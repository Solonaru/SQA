import { Component, OnInit } from '@angular/core';
import { Item } from '../../../entities/classes/item/item';
import { ItemService } from '../../../providers/services/item/item.service';
import { CategoryService } from '../../../providers/services/category.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-shopping',
  templateUrl: './shopping.component.html',
  styleUrls: ['./shopping.component.css']
})
export class ShoppingComponent implements OnInit {

  items: Item[];

  constructor(private itemService: ItemService, private categoryService: CategoryService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.populateItems(this.route.snapshot.paramMap.get('cat'));
  }

  populateItems(categoryId: String) {
    this.categoryService.getCategoryById(categoryId).subscribe(category => {
      this.itemService.getListedItemsByCategoryId(category.id).subscribe(items => { this.items = items; });
    });
  }

}
