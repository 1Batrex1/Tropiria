import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { HttpClient } from "@angular/common/http";
import {environment} from "../../constants/environment";

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private http: HttpClient) { }

  getPhoto(photoName: string): Observable<Blob> {
    return this.http.get(environment.path.photo + "/" + photoName, {responseType: 'blob', observe: 'body'});
  }
}
