import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/providers/services/product.service';
import { Product } from 'src/app/entities/classes/product';

@Component({
  selector: 'app-product-mng',
  templateUrl: './product-mng.component.html',
  styleUrls: ['./product-mng.component.css']
})
export class ProductMngComponent implements OnInit {

  products: Product[];
  product: Product;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    this.productService.getProducts().subscribe(
      data => {
        return this.products = data;
      }
    );
  }

  onUpdate(product: Product) {
    this.product = product;
  }

  onDelete(product: Product) {
    this.product = product;
  }

  onAdd() {
    this.product = new Product();
  }

  onConfirmDelete() {
    this.productService.deleteProduct(this.product).subscribe(
      data => { location.reload(); }
    );
  }

  updateProduct() {
    this.productService.updateProduct(this.product).subscribe();
  }

  insertProduct() {
    this.productService.insertProduct(this.product).subscribe(
      data => { location.reload(); }
    );
  }

  onSubmit() {
    if (this.product.id !== undefined) {
      this.updateProduct();
    } else {
      this.insertProduct();
    }
  }

}
