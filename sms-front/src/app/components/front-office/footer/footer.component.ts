import { Component, OnInit } from '@angular/core';
import { AppTypeService } from 'src/app/providers/services/apptype.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(private apptype : AppTypeService) { }

  ngOnInit() {
  }

  appChanged = (evt : any) => {    
    console.log("Changed " + evt.target.checked);
    this.sendAppChanged(evt.target.checked ? 1 : 0);
    }

  sendAppChanged(i : Number){
    console.log("Number changed " + i);
    this.apptype.changeType(i).subscribe();

  }
}
