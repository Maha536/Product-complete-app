import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  errorMessage : string;
  autherized : boolean;

  // recieve authentication service in constructor
  // recieve Router service in constructor
  constructor(public auth : AuthenticationService, public router : Router) { 
      this.errorMessage = "Invalid Credentials!!!";
      this.autherized = true;
  }

  // method to check Login credentials
  checkLogin(txtLogin : HTMLInputElement, txtPass : HTMLInputElement){
    // need a service to authenticate
    if(this.auth.authenticate(txtLogin.value, txtPass.value)){
        // if user autherized navigate to product component
        this.autherized = true;
        this.router.navigate(['/product']);
    }else{
        this.autherized = false;
    }
  }

  ngOnInit() {
  }

}
