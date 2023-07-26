import { Component } from '@angular/core';
import {countryList} from "../CountryList";
import {AbstractControlOptions, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  countryList = countryList;
  registerForm: FormGroup = new FormGroup({});
  isSubmitted = false;
  passwordsMatch = true;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void{
    const controlOptions: AbstractControlOptions = { updateOn: 'submit' };

    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      country: ['', Validators.required]
    }, controlOptions);
  }

  submit(){
    this.isSubmitted = true;
    this.passwordsMatch = this.registerForm.get('password')?.value == this.registerForm.get('confirmPassword')?.value;
    if(this.registerForm.invalid)
      return;
  }

}
