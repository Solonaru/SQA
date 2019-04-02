import { Component, OnInit } from '@angular/core';
import { Job } from '../../../entities/classes/job';
import { JobService } from '../../../providers/services/job.service';
import { Router } from '@angular/router';
import { Location } from '../../../entities/classes/location';
import { LocationService } from '../../../providers/services/location.service';


@Component({
  selector: 'app-careers',
  templateUrl: './careers.component.html',
  styleUrls: ['./careers.component.css']
})

export class CareersComponent implements OnInit {

  location: Location;
  locations: Location[];

  job: Job;
  jobs: Job[];

  constructor(private jobService: JobService, private locationService: LocationService, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit() {
    this.populateJobs();
    this.populateLocations();
  }

  populateJobs() {
    this.jobService.getJobs().subscribe(data => { this.jobs = data; });
  }

  populateLocations() {
    this.locationService.getLocations().subscribe(data => { this.locations = data; });
  }

  onClick(job: Job) {
    this.router.navigate(['job/', { job: job.id }]);
  }

  onLocationChanged(_location: Location) {
    this.jobService.getJobsByLocation(_location).subscribe(data => {this.jobs = data; });
    console.log(this.jobs);
  }

}

