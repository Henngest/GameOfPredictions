import { Component } from '@angular/core';
import {filter, fromEvent, map} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'gop-frontend';

  isLoggedIn = false;

  constructor() {
  }

  ngOnInit() : void{
    // if(localStorage.getItem("jwt") !== "")
    //   this.isLoggedIn = true;
    // fromEvent<StorageEvent>(window, 'storage').pipe(
    //   filter(event => event.storageArea === localStorage),
    //   filter(event => event.key === "jwt"),
    //   map(event => event.newValue)
    // ).subscribe(
    //   value => {
    //     if(value == null)
    //       this.isLoggedIn = false;
    //     else
    //       this.isLoggedIn = true;
    //     console.log(value);
    //   }
    // )
    // console.log(this.isLoggedIn)
  }

  logout(){
    localStorage.removeItem("jwt");
    this.isLoggedIn = false;
  }
}
