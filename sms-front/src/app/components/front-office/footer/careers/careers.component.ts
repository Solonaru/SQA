import { Component, OnInit } from '@angular/core';
import { CityService } from 'src/app/providers/services/city.service';
import { City } from 'src/app/entities/classes/city';

@Component({
  selector: 'app-careers',
  templateUrl: './careers.component.html',
  styleUrls: ['./careers.component.css']
})

export class CareersComponent implements OnInit {
  city: City;
  cities: City[];


  constructor(private cityService : CityService) { }
  ngOnInit() {
    this.populateCities();
  }

  populateCities() {
    this.cityService.getCities().subscribe(data => { this.cities = data; });
  }

}
