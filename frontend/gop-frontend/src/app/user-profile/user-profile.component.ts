import {Component} from '@angular/core';
import {User} from "../interfaces/user";
import {UserProfileService} from "../user-profile.service";
import {AuthenticationService} from "../authentication.service";
import {DecimalPipe} from "@angular/common";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
  providers: [DecimalPipe]
})
export class UserProfileComponent {
  user: User | undefined;

  constructor(
    private userProfileService: UserProfileService,
    private authService: AuthenticationService) { }

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
