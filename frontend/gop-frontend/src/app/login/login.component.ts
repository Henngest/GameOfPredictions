import { Component } from '@angular/core';
import {AbstractControlOptions, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup = new FormGroup({});
  isSubmitted = false

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    const controlOptions: AbstractControlOptions = { updateOn: 'submit' };

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    }, controlOptions);
  }

  submit(){
    this.isSubmitted = true;
    if(this.loginForm.invalid)
      return;
  }
}
