import {Component, OnInit} from '@angular/core';
import {AbstractControlOptions, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {countryList} from "../../CountryList";
import {AuthenticationService} from "../../services/authentication.service";
import {UserProfileService} from "../../services/user-profile.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  countryList = countryList;
  editForm: FormGroup = new FormGroup({});
  errorMsg: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthenticationService,
              private userProfileService: UserProfileService,
              private router: Router) {
  }

  ngOnInit() {
    const controlOptions: AbstractControlOptions = {updateOn: 'submit'};

    this.editForm = this.formBuilder.group({
      country: ['', Validators.required]
    }, controlOptions);
  }

  submit() {
    const currentUser = this.authService.getUsername();
    if (currentUser && this.editForm.get('country')?.value != "") {
      this.userProfileService.editUserProfile(currentUser, this.editForm.get('country')?.value)
        .subscribe(_ => this.router.navigateByUrl("/profile"));
    } else {
      this.errorMsg = true;
    }
  }
}
