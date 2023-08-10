import {Component, OnInit} from '@angular/core';
import {countryList} from "../../CountryList";
import {AbstractControlOptions, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {Errors} from "../../interfaces/errors";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  countryList = countryList;
  registerForm: FormGroup = new FormGroup({});
  isSubmitted = false;
  passwordsMatch = true;
  errors: Errors | undefined;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthenticationService,
              private router: Router) {
  }

  ngOnInit() {
    const controlOptions: AbstractControlOptions = {updateOn: 'submit'};

    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      country: ['', Validators.required]
    }, controlOptions);
  }

  submit() {
    this.isSubmitted = true;
    this.passwordsMatch = this.registerForm.get('password')?.value == this.registerForm.get('confirmPassword')?.value;
    if (this.registerForm.invalid || !this.passwordsMatch)
      return;
    this.authService.register(this.registerForm.get('username')?.value,
      this.registerForm.get('password')?.value,
      this.registerForm.get('confirmPassword')?.value,
      this.registerForm.get('country')?.value)
      .subscribe({
          next: (value) => {
            if (value) {
              this.router.navigateByUrl("/competitions");
            }
          },
          error: (err) => {
            this.errors = {errorMessages: err.error.errors};
          }
        }
      )
  }

}
