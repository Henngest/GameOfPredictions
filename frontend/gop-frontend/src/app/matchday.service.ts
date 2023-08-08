import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Season} from "./interfaces/season";
import {Matchday} from "./interfaces/matchday";

@Injectable({
  providedIn: 'root'
})
export class MatchdayService {

  constructor(
    private http: HttpClient
  ) {
  }

  getAllBySeasonId(seasonId: number): Observable<Matchday[]> {
    return this.http.get<Matchday[]>(`/api/seasons/${seasonId}/matchdays`);
  }

  getById(id: number, seasonId: number): Observable<Matchday | undefined> {
    return this.http.get<Matchday | undefined>(`/api/seasons/${seasonId}/matchdays/${id}`);
  }
}
