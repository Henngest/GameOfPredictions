import { Component } from '@angular/core';
import {countryList} from "../CountryList";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  countryList = countryList;

}
