import { Component } from '@angular/core';
import {User} from "../interfaces/user";

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent {

  users: User[] | undefined;
}
