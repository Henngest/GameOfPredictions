import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn: boolean | undefined;

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.authService.isLoggedIn().subscribe(value => this.isLoggedIn = value);
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl("/competitions");
  }

  getUsername() {
    return this.authService.getUsername();
  }

  toHomePage() {
    this.router.navigateByUrl("/competitions");
  }
}
