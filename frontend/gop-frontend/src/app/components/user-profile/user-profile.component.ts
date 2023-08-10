import {Component, OnInit} from '@angular/core';
import {User} from "../../interfaces/user";
import {UserProfileService} from "../../services/user-profile.service";
import {AuthenticationService} from "../../services/authentication.service";
import {DecimalPipe} from "@angular/common";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
  providers: [DecimalPipe]
})
export class UserProfileComponent implements OnInit {
  user: User | undefined;

  constructor(
    private userProfileService: UserProfileService,
    private authService: AuthenticationService) {
  }

  ngOnInit() {
    const currentUser = this.authService.getUsername();
    if (currentUser) {
      this.userProfileService.getUserProfile(currentUser).subscribe(
        (user: User) => {
          this.user = user;
        }
      );
    }
  }
}
