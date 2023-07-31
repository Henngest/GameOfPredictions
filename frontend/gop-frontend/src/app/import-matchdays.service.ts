import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ImportMatchdaysService {

  constructor(private http: HttpClient) { }

  importMatchdays(seasonId: number, file: File){
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    return this.http.post<any>(`/api/seasons/${seasonId}/matchdays/import`, formData)
  }
}
