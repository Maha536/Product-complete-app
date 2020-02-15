import { Component, OnInit } from '@angular/core';
import { ProductDataService } from 'src/app/services/data/product-data.service';
import { Product } from 'src/app/models/product.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html'
})
export class ProductListComponent implements OnInit {

  products : Array<Product>; // will be initilized from data coming from server
  // dependent on product-data-service
  constructor(private productService : ProductDataService, public router : Router) { 

  }

  add(){
    // navigate to product-add component
    this.router.navigate(['/product-add']);
  }

  update(id:number){
    // navigate to update Component (associate id also)
    this.router.navigate(['/product-update/' + id]);
  }

  delete(id:number){
    // call product data service to delete
    this.productService.deleteProduct(id).subscribe((response) => {
        console.log(response);
        // call getAllProducts (selective loading)
        this.productService.getAllProducts().subscribe((response:any) => this.products = response);
        
        // navigate to same component (caching issues)
        // this.router.navigate(['/product']);
    });
  }



  ngOnInit() {
    // load data from server on initialization
    // need to call getAllProducts() method of ProductDataService
    // this.products = this.productService.getAllProducts();
    // response : response data from server : Array of products
    this.productService.getAllProducts().subscribe((response:any) => this.products = response);
  }

}
