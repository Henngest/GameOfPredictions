import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Competition} from "../interfaces/competition";
import {Season} from "../interfaces/season";

@Injectable({
  providedIn: 'root'
})
export class SeasonsService {

  constructor(
    private http: HttpClient
  ) {
  }

  getAllByCompetitionId(competitionId: number): Observable<Season[]> {
    return this.http.get<Season[]>(`/api/competition/${competitionId}/seasons`);
  }

  getById(id: number, competitionId: number): Observable<Season | undefined> {
    return this.http.get<Season | undefined>(`/api/competition/${competitionId}/seasons/${id}`);
  }
}
