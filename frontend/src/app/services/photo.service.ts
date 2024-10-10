import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { HttpClient } from "@angular/common/http";
import {environment} from "../../constants/environment";
import {Photo} from "../entities/photo";

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private http: HttpClient) { }

  getPhoto(photoName: string): Observable<Blob> {
    return this.http.get(environment.path.photo + "/" + photoName, {responseType: 'blob', observe: 'body'});
  }

  getAllPhotos(size: string, page : string): Observable<GetAllPhotosResponse> {
    return this.http.get<GetAllPhotosResponse>(environment.path.photo + "?size=" + size + "&page=" + page);
  }
}

interface GetAllPhotosResponse {
  content: Photo[],
  totalPages: number,
  totalElements: number,

}
