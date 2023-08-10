import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Prediction} from "../interfaces/prediction";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PredictionsService {

  constructor(
    private http: HttpClient
  ) {
  }

  submitPredictions(predictions: Prediction[]): Observable<any> {
    return this.http.post<any>("/api/predict", predictions);
  }

  getPredictionsForMatchdayByUser(matchdayId: number, user: string): Observable<Prediction[]> {
    return this.http.get<Prediction[]>(`/api/matchday/${matchdayId}/predictionsByUser/${user}`);
  }
}
