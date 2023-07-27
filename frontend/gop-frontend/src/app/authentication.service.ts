import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Jwt} from "./interfaces/jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  register(username: string, password: string, confirmPassword: string, country: string): Observable<Jwt | undefined> {
    return this.httpClient.post<Jwt | undefined>("/api/auth/register", {username, password, confirmPassword, country})
  }

  login(username: string, password: string): Observable<Jwt | undefined> {
    return this.httpClient.post<Jwt | undefined>("/api/auth/login", {username, password});
  }
}
