import { Component, OnInit } from '@angular/core';
import { FeedbackService } from '../../../providers/services/feedback.service';
import { Feedback } from '../../../entities/classes/feedback';

@Component({
  selector: 'app-feedback-mng',
  templateUrl: './feedback-mng.component.html',
  styleUrls: ['./feedback-mng.component.css']
})
export class FeedbackMngComponent implements OnInit {

  feedbacks: Feedback[];

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit() {
    this.populateFeedbacks();
  }

  populateFeedbacks() {
    this.feedbackService.getFeedbacks().subscribe(data => {
      this.feedbacks = data;
    });
  }

}
