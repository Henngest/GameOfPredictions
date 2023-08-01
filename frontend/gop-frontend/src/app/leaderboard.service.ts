import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./interfaces/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LeaderboardService {

  constructor(private http: HttpClient) {
  }

  getUsersSortedByRating() {

  }
}
