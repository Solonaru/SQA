import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../canvasjs.min';
import { Category } from '../../../entities/classes/category';
import { Item } from '../../../entities/classes/item/item';
import { CategoryService } from '../../../providers/services/category.service';
import { ItemService } from '../../../providers/services/item/item.service';
import { ProductStatsService } from '../../../providers/services/stats/productstats.service';

@Component({
  selector: 'app-product-price-sales-chart',
  templateUrl: './product-price-sales-chart.component.html',
  styleUrls: ['./product-price-sales-chart.component.css']
})
export class ProductPriceSalesChartComponent implements OnInit {

  categories: Category[];
  productsStatisticData: Map<Number, Number>;

  items: Item[];
  item: Item;
  private selectedCategory;
  private selectedValue;

  private dataPoints = [];
  private dataPoints2 = [];

  constructor(private categoryService: CategoryService, private itemService: ItemService, private productStatsService: ProductStatsService) { }

  ngOnInit() {
    this.populateCategories();
  }

  populateCategories() {
    this.categoryService.getFrontOfficeCategories().subscribe(data => { this.categories = data });
  }

  populateChart(productId: Number) {
    this.productStatsService.getProductsStatisticDataBasedOnPrice(productId).subscribe(statData => {
      this.generateData(statData);
      this.generateChart();
    });
  }

  generateChart() {
    console.log("DATA POINTS: ");
    console.log(this.dataPoints);
    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Product Sales based on Price"
      },
      subtitles: [{

      }],
      toolTip: {
        shared: true
      },
      axisX: {
        title: "Price",
        crosshair: {
          enabled: true,
          snapToDataPoint: true
        }
      },
      axisY: {
        title: "Total sales",
        titleFontColor: "#4F81BC",
        crosshair: {
          enabled: true,
          snapToDataPoint: true
        },
        lineColor: "#4F81BC",
        tickColor: "#4F81BC"
      },
      axisY2: {
        title: "Quantity",
        titleFontColor: "#C0504E",
        lineColor: "#C0504E",
        tickColor: "#C0504E"
      },
      data: [
        {
          name: "Total sales",
          type: "spline",
          markerType: "square",
          xValueFormatString: "#,##0.00 lei",
          yValueFormatString: "#,##0.00 lei",
          dataPoints: this.dataPoints
        },
        {
          name: "Quantity",
          visible: false,
          yValueFormatString: "### units",
          dataPoints: this.dataPoints2
        }
      ]
    });

    chart.render();
  }

  generateData(statData: Map<Number, Number>) {
    let y = 0;
    let x = 0;

    for (var key in statData) {
      if (statData.hasOwnProperty(key)) {
        y = <any>statData[key];
        x = <any>key;
        this.dataPoints.push({ x: parseFloat("" + x), y: parseInt("" + y, 10) });
        this.dataPoints2.push({ x: parseInt("" + x, 10), y: parseInt("" + (y / x), 10) });
      }
    };
  }

  changeCategory() {
    this.itemService.getListedItemsByCategoryId(this.selectedCategory).subscribe(data => { this.items = data });
  }

  changeItem() {
    this.dataPoints = [];
    this.dataPoints2 = [];

    this.itemService.getItemById(this.selectedValue).subscribe(data => {
      this.item = data;
      this.populateChart(this.item.id);
    });
  }

}
