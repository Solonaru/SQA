
import { Component, OnInit } from '@angular/core';
import { Job } from '../../../../entities/job';
import { JobService } from '../../../../providers/services/job.service';

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {

  job : Job;
  jobs: Job[];
  


  constructor(private jobService:JobService) { }

  ngOnInit() {
    this.populateJobs();
  }

  populateJobs() {
    this.jobService.getJobs().subscribe(data => { this.jobs = data; });
  }
}

