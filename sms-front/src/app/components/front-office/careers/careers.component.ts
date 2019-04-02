
import { Component, OnInit } from '@angular/core';
import { Job } from '../../../entities/classes/job';
import { JobService } from '../../../providers/services/job.service';
import { Router } from '../../../../../node_modules/@angular/router';


@Component({
  selector: 'app-careers',
  templateUrl: './careers.component.html',
  styleUrls: ['./careers.component.css']
})

export class CareersComponent implements OnInit {

  job: Job;
  jobs: Job[];

  constructor(private jobService: JobService, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit() {
    this.populateJobs();
  }

  populateJobs() {
    this.jobService.getJobs().subscribe(data => { this.jobs = data; });
  }

  onClick(job: Job) {
    this.router.navigate(['job/', { job: job.id }]);
  }

}

