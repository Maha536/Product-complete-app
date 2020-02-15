import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDataService } from 'src/app/services/data/product-data.service';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {


  productId : number;
  product : Product;

  // dependecy on Product Data Service
  constructor(public activatedRoute : ActivatedRoute, public productService : ProductDataService, public router : Router) { 
    // need to get the id from url
    this.activatedRoute.params.subscribe((parameter) => this.productId = parameter["productId"]);
  }

  ngOnInit() {
    // need to fetch data of a given id
    this.productService.getOneProduct(this.productId).subscribe((response:any) => this.product = response);
  }



  updateProduct(txtName:HTMLInputElement, txtCategory:HTMLInputElement, txtCost:HTMLInputElement):void{
    
    // create a product object of updated info
    let product = new Product(txtName.value, txtCategory.value, parseInt(txtCost.value));

    // need to send it to server using product Data Service
    this.productService.updateProduct(this.productId,product).subscribe(
      (response) => {
        console.log(response);
        // navigate to product-list
        this.router.navigate(['/product']);
      });


    // reset the form
    txtName.value = "";
    txtCategory.value = "";
    txtCost.value = "";

    
}

}
