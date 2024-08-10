import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {environment} from "../../constants/environment";
import {Observable} from "rxjs";
import {Species} from "../entities/species";

@Injectable({
  providedIn: 'root'
})
export class SpeciesService {

  constructor(private http :HttpClient) { }


  getSpecies(): Observable<Species[]> {
    return this.http.get<Species[]>(environment.path.species);
  }
}
