import { Component, OnInit } from '@angular/core';
import { LocationService } from '../../../providers/services/location.service';
import { Location } from '../../../entities/classes/location';

@Component({
  selector: 'app-location-mng',
  templateUrl: './location-mng.component.html',
  styleUrls: ['./location-mng.component.css']
})
export class LocationMngComponent implements OnInit {

  location: Location;
  locations: Location[];

  constructor(private locationService: LocationService) { }

  ngOnInit() {
    this.populateLocations();
  }

  populateLocations() {
    this.locationService.getLocations().subscribe(data => { this.locations = data; });
  }

  onAdd() {
    this.location = new Location();
  }

  onUpdate(location: Location) {
    this.location = location;
  }

  onDelete(location: Location) {
    this.location = location;
  }

  onConfirmDelete() {
    this.locationService.deleteLocation(this.location).subscribe(data => { location.reload(); });
  }

  onSubmit() {
    if (this.location.id != undefined) {
      this.updateLocation();
    } else {
      this.insertLocation();
    }
  }

  insertLocation() {
    this.locationService.insertLocation(this.location).subscribe(date => { location.reload(); });
  }

  updateLocation() {
    this.locationService.updateLocation(this.location).subscribe();
  }

}
