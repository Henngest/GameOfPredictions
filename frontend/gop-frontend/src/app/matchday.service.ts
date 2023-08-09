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

  importMatchdays(seasonId: number, file: File){
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    return this.http.post<any>(`/api/seasons/${seasonId}/matchdays/import`, formData)
  }

  importMatchdayResults(seasonId: number, matchdayId: number, file: File){
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    return this.http.post<any>(`/api/seasons/${seasonId}/matchdays/${matchdayId}/importResults`, formData)
  }
}
