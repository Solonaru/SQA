
import { Component, OnInit } from '@angular/core';
import { Job } from '../../../entities/job';
import { JobService } from '../../../providers/services/job.service';


@Component({
  selector: 'app-careers',
  templateUrl: './careers.component.html',
  styleUrls: ['./careers.component.css']
})

export class CareersComponent implements OnInit {

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

