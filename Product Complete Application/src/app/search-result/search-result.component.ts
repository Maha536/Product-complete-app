import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html'
})
export class SearchResultComponent implements OnInit {

  searchData : string;  // variable to receive data
  // special service : ActivatedRoute to access url data
  // object is injected...
  constructor(public activatedRoute : ActivatedRoute) { 
    // data associated with url is known as param (parameter)
    // this.activatedRoute.params.subscribe(<call back function>)  
    // whenever data is available call the function
    /*this.activatedRoute.params.subscribe(function(<param>){

    });*/
    // parameter is array that hold url data
    this.activatedRoute.params.subscribe((parameter) => this.searchData = parameter["searchText"]);
  }

  

  ngOnInit() {
  }

}
