
import { Component, OnInit } from '@angular/core';
import { Job } from '../../../../entities/classes/job';
import { JobService } from '../../../../providers/services/job.service';
import { ActivatedRoute } from '../../../../../../node_modules/@angular/router';

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {

  jobId : String;
  job : Job;

  constructor(private jobService:JobService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.jobId = this.route.snapshot.paramMap.get('job');
    this.populateJob();
  }

  populateJob() {
    this.jobService.getJobById(this.jobId).subscribe(data => { this.job = data });
  }
}

