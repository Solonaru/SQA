import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Feedback } from '../../../entities/classes/feedback';
import { FeedbackService } from '../../../providers/services/feedback.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  name: String;
  email: String;
  subject: String;
  message: String;

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit() {
  }

  onSubmit() {
    let feedback: Feedback = new Feedback(this.name, this.email, this.subject, this.message);
    this.feedbackService.insertFeedback(feedback).subscribe(data => { });
    this.clearForm();
  }

  clearForm() {
    this.name = "";
    this.email = "";
    this.subject = "";
    this.message = "";
  }
}
