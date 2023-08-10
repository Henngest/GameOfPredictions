import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Competition} from "../interfaces/competition";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CompetitionsService {

  constructor(
    private http: HttpClient
  ) {
  }

  getAll(): Observable<Competition[]> {
    return this.http.get<Competition[]>("/api/competitions");
  }

  getById(id: number): Observable<Competition | undefined> {
    return this.http.get<Competition | undefined>(`/api/competitions/${id}`);
  }
}
