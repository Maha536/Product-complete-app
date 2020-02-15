import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

  // receive authentication service injected
  constructor(public auth : AuthenticationService) { 

  }

  ngOnInit() {
  }

}
