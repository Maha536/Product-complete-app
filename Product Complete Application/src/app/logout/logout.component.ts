import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html'
})
export class LogoutComponent implements OnInit {

  // receive authentication service
  constructor(public auth : AuthenticationService) { 

  }

  // life-cycle hook method :gets called before component html is displayed
  ngOnInit() {
    // before component displays : need to logout
    /// call the logout method of service
    this.auth.logout();
  }

}
