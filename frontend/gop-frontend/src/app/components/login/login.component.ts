import {Component, OnInit} from '@angular/core';
import {AbstractControlOptions, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({});
  isSubmitted = false;
  errorMessage: string | undefined;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthenticationService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const controlOptions: AbstractControlOptions = {updateOn: 'submit'};

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    }, controlOptions);

    this.route.queryParamMap.pipe(
      map(params => params.has('errorMessage') ? params.get('errorMessage')!! : undefined)
    ).subscribe(value => this.errorMessage = value)
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
