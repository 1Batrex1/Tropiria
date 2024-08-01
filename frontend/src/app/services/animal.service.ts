import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Animal} from "../entities/animal";
import {Observable} from "rxjs";
import {environment} from "../../environment/enviroment";

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  constructor(private http: HttpClient) { }


  getAnimalsForSale(): Observable<getAnimalsForSale> {
    return this.http.get<getAnimalsForSale>(environment.path.animals+'/for-sale?size=3');
  }
}


interface getAnimalsForSale {
  content: Animal[];
  totalElements: number;
  totalPages: number;
  last: boolean;
  size: number;
  number: number;
  numberOfElements: number;
  first: boolean;
  empty: boolean;
}
