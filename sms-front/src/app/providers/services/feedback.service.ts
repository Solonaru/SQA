import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { map } from 'rxjs/operators';
import { Feedback } from '../../entities/classes/feedback';


@Injectable({
    providedIn: 'root'
})

export class FeedbackService {

    private BASE_URL: string = "http://localhost:8090/feedback/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    getFeedbacks() {
        return this.http.get(this.BASE_URL + 'all').pipe(map((res: Feedback[]) => { return res }));
    }

    getFeedbackById(feedbackId: String) {
        return this.http.get(this.BASE_URL + feedbackId)
            .pipe(map((res: Feedback) => { return res }));
    }

    insertFeedback(feedback: Feedback) {
        return this.http.post<Feedback>(this.BASE_URL + 'add', JSON.stringify(feedback), this.httpOptions)
            .pipe(map((resp: Feedback) => { return resp }));
    }

    updateFeedback(feedback: Feedback) {
        return this.http.put<Feedback>(this.BASE_URL + 'update', JSON.stringify(feedback), this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }

    deleteFeedback(feedback: Feedback) {
        return this.http.delete(this.BASE_URL + 'delete/' + feedback.id, this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }

}