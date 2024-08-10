import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Morph} from "../entities/morph";
import {environment} from "../../constants/environment";

@Injectable({
  providedIn: 'root'
})
export class MorphService {

  constructor(private http :HttpClient) { }

  gerMorphs():Observable<Morph[]> {
    return this.http.get<Morph[]>(environment.path.morph);
  }
}
