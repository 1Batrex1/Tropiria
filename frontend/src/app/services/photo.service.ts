import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {environment} from "../../environment/enviroment";

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private http: HttpClient) { }

  getPhoto(photoName: string): Observable<Blob> {
    return this.http.get(environment.photos + "/" + photoName, {responseType: 'blob', observe: 'body'});
  }
}
