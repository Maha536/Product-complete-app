import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  //  check userid/pass and authenticate 
  authenticate(userid : string, password : string): boolean{
    // hard-coded validation
    if(userid === "First" && password === "abc"){
      //  need to maintain session
      // sessionStorage object is auto available
      sessionStorage.setItem("user", userid);
      return true;
    }else{
      return false;
    }
  }

  // to check if user if logged in or not
  isUserLoggedIn(): boolean{
    // check if sessionStorage contains key 'user'
    let user = sessionStorage.getItem('user');
    if(user == null)
      return false;
    return true;  
  }

  // logout
  logout(){
    // remove the key/value pair of 'user'
    sessionStorage.removeItem('user');
    // sessionStorage.clear();
  }

  // get user Details
  getUserDetails():string{
    let user = sessionStorage.getItem('user');
    return user;
  }
}
