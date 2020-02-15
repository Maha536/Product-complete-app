import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'  
})
export class AppComponent {
  title = 'route-base-app';

  // recieve authentication service injected
  constructor(private router : Router, public auth : AuthenticationService){

  }

  searchCall(srch : HTMLInputElement){
    // navigate to search-result component
    // special class is needed to navigate programmatically : Router (Service)
    // object of Router class is auto injected
    if(srch.value != ""){
      this.router.navigate(['/search/' + srch.value]); //  connect data
    }
  }
}
