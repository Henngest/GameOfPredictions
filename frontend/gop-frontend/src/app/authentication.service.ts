import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {BehaviorSubject, catchError, map, Observable, of, throwError} from "rxjs";
import {Jwt} from "./interfaces/jwt";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private isLoggedInSubject: BehaviorSubject<boolean>;

  constructor(private httpClient: HttpClient,
              private jwtHelper: JwtHelperService) {
    if (localStorage.getItem("jwt")) {
      this.isLoggedInSubject = new BehaviorSubject<boolean>(true);
    } else {
      this.isLoggedInSubject = new BehaviorSubject<boolean>(false);
    }
  }

  register(username: string, password: string, confirmPassword: string, country: string): Observable<Jwt | undefined> {
    return this.httpClient.post<Jwt | undefined>("/api/auth/register", {username, password, confirmPassword, country})
  }

  login(username: string, password: string): Observable<boolean> {
    return this.httpClient.post<Jwt | undefined>("/api/auth/login", {username, password}).pipe(
      map(value => {
        if(value && value.jwt) {
          localStorage.setItem("jwt", value.jwt);
          this.isLoggedInSubject.next(true);
          return true;
        } else {
          return false;
        }
      }),
      catchError((err) => {
        return throwError(err);
      })
    );
  }

  logout() {
    localStorage.removeItem("jwt");
    this.isLoggedInSubject.next(false);
  }

  isLoggedIn(): Observable<boolean> {
    return this.isLoggedInSubject.asObservable();
  }

  getUsername(): string | undefined {
    const token = localStorage.getItem("jwt");
    if (token) {
      const tokenPayload = this.jwtHelper.decodeToken(token);
      console.log(tokenPayload.sub);
      return tokenPayload.sub;
    }
    return undefined;
  }
}
