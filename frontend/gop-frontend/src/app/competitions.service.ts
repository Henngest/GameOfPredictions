import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Competition} from "./interfaces/competition";
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
}
