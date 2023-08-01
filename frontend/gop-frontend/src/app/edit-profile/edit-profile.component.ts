import {Component} from '@angular/core';
import {AbstractControlOptions, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {countryList} from "../CountryList";
import {AuthenticationService} from "../authentication.service";
import {UserProfileService} from "../user-profile.service";
import {User} from "../interfaces/user";


@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent {
  countryList = countryList;
  editForm: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder,
              private authService: AuthenticationService,
              private userProfileService: UserProfileService) {
  }

  ngOnInit() {
    const controlOptions: AbstractControlOptions = {updateOn: 'submit'};

    this.editForm = this.formBuilder.group({
      country: ['', Validators.required]
    }, controlOptions);
  }

  submit() {
    const currentUser = this.authService.getUsername();
    if (currentUser) {
      console.log(this.editForm.get('country')?.value)
      this.userProfileService.editUserProfile(currentUser,this.editForm.get('country')?.value).subscribe();
    }
  }
}
