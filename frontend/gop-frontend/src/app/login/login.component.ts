import {Component} from '@angular/core';
import {AbstractControlOptions, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup = new FormGroup({});
  isSubmitted = false;
  errorMessage: string | undefined;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthenticationService,
              private router: Router) {
  }

  ngOnInit() {
    const controlOptions: AbstractControlOptions = {updateOn: 'submit'};

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    }, controlOptions);
  }

  submit() {
    this.isSubmitted = true;
    if (this.loginForm.invalid)
      return;

    this.authService.login(this.loginForm.get('username')?.value,
      this.loginForm.get('password')?.value).subscribe({
      next: value => {
        if (value) {
          this.router.navigateByUrl("/competitions");
        }
      }, error: err => {
        this.errorMessage = "Invalid credentials. Try again."
      }
    })
  }
}
