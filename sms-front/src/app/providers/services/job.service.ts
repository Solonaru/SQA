import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { map } from 'rxjs/operators';
import { Job } from '../../entities/job';


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


    getJobById(jobId: Number) {
        return this.http.get(this.BASE_URL + jobId)
            .pipe(map((res: Job) => { return res }));
    }

    
}