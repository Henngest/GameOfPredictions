import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Fixture} from "../interfaces/fixture";

@Injectable({
  providedIn: 'root'
})
export class FixtureService {

  constructor(private http: HttpClient) {
  }

  editFixture(matchdayId: number,
              id: number,
              startTime: string,
              homeTeamWinCoefficient: number,
              drawCoefficient: number,
              awayTeamWinCoefficient: number): Observable<Fixture> {
    const data = {
      startTime: startTime,
      homeTeamWinCoefficient: homeTeamWinCoefficient,
      drawCoefficient: drawCoefficient,
      awayTeamWinCoefficient: awayTeamWinCoefficient
    }
    return this.http.put<Fixture>(`api/matchdays/${matchdayId}/fixtures/edit/${id}`, data);
  }

  getById(matchdayId: number, fixtureId: number): Observable<Fixture | undefined> {
    return this.http.get<Fixture | undefined>(`/api/matchdays/${matchdayId}/fixtures/${fixtureId}`);
  }
}
