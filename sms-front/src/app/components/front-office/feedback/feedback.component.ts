import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
  name:String;
  email:String;
  subject:String;
  message:String;

  constructor() { }

  ngOnInit() {
  }
 
  onSubmit() {
   alert('Thank you for sharing with us!');
  }
 }
