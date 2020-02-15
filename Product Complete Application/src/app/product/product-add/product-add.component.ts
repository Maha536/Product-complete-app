import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductDataService } from 'src/app/services/data/product-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html'
})
export class ProductAddComponent implements OnInit {

  // dependency over product data service
  constructor(public productService: ProductDataService, public router : Router) { }

  ngOnInit() {
  }


  saveProduct(txtName:HTMLInputElement, txtCategory:HTMLInputElement, txtCost:HTMLInputElement):void{
    
    let product = new Product(txtName.value, txtCategory.value, parseInt(txtCost.value));

    // need to send it to server using product Data Service
    this.productService.addNewProduct(product).subscribe(
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
