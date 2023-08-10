import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../interfaces/page";

@Injectable({
  providedIn: 'root',
})
export class LeaderboardService {

  constructor(private http: HttpClient) {
  }

  getUsersSortedByRating(page: number, size: number, sortDirection?: string): Observable<Page> {
    if (sortDirection) {
      return this.http.get<Page>(`/api/users/getAllUsers?page=${page}&size=${size}&sortDirection=${sortDirection}`)
    } else {
      return this.http.get<Page>(`/api/users/getAllUsers?page=${page}&size=${size}`);
    }
  }
}
