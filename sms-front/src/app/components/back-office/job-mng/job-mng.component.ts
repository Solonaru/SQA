import { Component, OnInit } from '@angular/core';
import { Job } from '../../../entities/classes/job';
import { JobService } from '../../../providers/services/job.service';

@Component({
  selector: 'app-job-mng',
  templateUrl: './job-mng.component.html',
  styleUrls: ['./job-mng.component.css']
})
export class JobMngComponent implements OnInit {

  job: Job;
  jobs: Job[];

  constructor(private jobService: JobService) { }

  ngOnInit() {
    this.populateJobs();
  }

  populateJobs() {
    this.jobService.getJobs().subscribe(data => { this.jobs = data; });
  }

  onAdd() {
    this.job = new Job();
  }

  onUpdate(job: Job) {
    this.job = job;
  }

  onDelete(job: Job) {
    this.job = job;
  }

  onConfirmDelete() {
    this.jobService.deleteJob(this.job).subscribe(data => { location.reload(); });
  }

  onSubmit() {
    if (this.job.id != undefined) {
      this.updateJob();
    } else {
      this.insertJob();
    }
  }

  insertJob() {
    this.jobService.insertJob(this.job).subscribe(date => { location.reload(); });
  }

  updateJob() {
    this.jobService.updateJob(this.job).subscribe();
  }
}
