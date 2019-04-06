import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { map } from 'rxjs/operators';
import { Job } from '../../entities/classes/job';
import { Location } from '../../entities/classes/location';


@Injectable({
    providedIn: 'root'
})

export class JobService {

    private BASE_URL: string = "http://localhost:8090/job/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    getJobs() {
        return this.http.get(this.BASE_URL + 'all').pipe(map((res: Job[]) => { return res }));
    }

    getJobsByLocation(location: Location) {
        return this.http.put(this.BASE_URL + 'all/location', JSON.stringify(location), this.httpOptions)
            .pipe(map((resp: Job[]) => { return resp }));
    }

    getJobById(jobId: String) {
        return this.http.get(this.BASE_URL + jobId)
            .pipe(map((res: Job) => { return res }));
    }

    insertJob(job: Job) {
        return this.http.post<Job>(this.BASE_URL + 'add', JSON.stringify(job), this.httpOptions)
            .pipe(map((resp: Job) => { return resp }));
    }

    updateJob(job: Job) {
        return this.http.put<Job>(this.BASE_URL + 'update', JSON.stringify(job), this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }

    deleteJob(job: Job) {
        return this.http.delete(this.BASE_URL + 'delete/' + job.id, this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }

}