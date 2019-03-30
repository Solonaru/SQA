import { Component, OnInit } from '@angular/core';
import { IImage } from '../../../entities/helper-classes/IImage';
import { Router } from '../../../../../node_modules/@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  imageUrls: (string | IImage)[] = [
	  { url: '../../../../assets/images/featured/featured1.jpg', caption: 'Pizzas', clickAction: () => this.onClick(8001) },
    { url: '../../../../assets/images/featured/featured2.jpg', caption: 'Drinks', clickAction: () => this.onClick(8013) },
    { url: '../../../../assets/images/featured/featured3.jpg', caption: 'Desserts', clickAction: () => this.onClick(8015) }
  ];
  height: string = '400px';
  minHeight: string;
  arrowSize: string = '30px';
  showArrows: boolean = true;
  disableSwiping: boolean = false;
  autoPlay: boolean = true;
  autoPlayInterval: number = 3333;
  stopAutoPlayOnSlide: boolean = true;
  debug: boolean = false;
  backgroundSize: string = 'cover';
  backgroundPosition: string = 'center center';
  backgroundRepeat: string = 'no-repeat';
  showDots: boolean = true;
  dotColor: string = '#FFF';
  showCaptions: boolean = true;
  captionColor: string = '#FFF';
  captionBackground: string = 'rgba(0, 0, 0, .35)';
  lazyLoad: boolean = false;
  hideOnNoSlides: boolean = false;
  width: string = '100%';

  constructor(private router: Router) { }

  ngOnInit() {
  }

  onClick(categoryId: Number) {
    /* Load the products page */
    // this.router.navigate(['products/', { cat: categoryId }]);
  }

}
