import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Animal} from "../entities/animal";
import {Observable} from "rxjs";
import {environment} from "../../constants/environment";
import {AuthService} from "./auth.service";
import {BrowserStorageService} from "./browser-storage.service";
import {response} from "express";

@Injectable({
  providedIn: 'root'
})
export class AnimalService {


  constructor(private http: HttpClient, private adminService: AuthService, private localStorage: BrowserStorageService) {
  }

  getParents(): Observable<Animal[]> {
    return this.http.get<Animal[]>(environment.path.animals + '/parents');
  }

  getAnimalsForSale(): Observable<getAnimalsForSale> {
    return this.http.get<getAnimalsForSale>(environment.path.animals + '/for-sale?size=4');
  }

  getAnimals(number: number, size: number): Observable<getAnimals> {
    return this.http.get<getAnimals>(environment.path.animals + '?page=' + number + '&size=' + size);
  }

  deleteAnimal(id: number): Observable<any>  {
    return this.http.delete(environment.path.animals + '/' + id);
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

interface getAnimals {

  content: Animal[];

  totalElements: number;

  totalPages: number;

  size: number;

  number: number;

}
