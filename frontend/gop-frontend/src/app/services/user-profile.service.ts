import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  constructor(private http: HttpClient) { }

  getUserProfile(username: string): Observable<User> {
    return this.http.get<User>('api/users', {params:{username}});
  }

  editUserProfile(username: string, country: string): Observable<User> {
    const data = {
      username: username,
      country: country
    }
    return this.http.put<User>(`api/users/edit`,data);
  }
}
