import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Animal} from "../entities/animal";
import {Observable} from "rxjs";
import {environment} from "../../constants/environment";
import {AuthService} from "./auth.service";
import {BrowserStorageService} from "./browser-storage.service";
import {response} from "express";
import {Form} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class AnimalService {


  constructor(private http: HttpClient, private adminService: AuthService, private localStorage: BrowserStorageService) {
  }

  getParents(): Observable<Animal[]> {
    return this.http.get<Animal[]>(environment.path.animals + '/parents');
  }

  getAnimalsForSale(size: number): Observable<getAnimalsForSale> {
    return this.http.get<getAnimalsForSale>(environment.path.animals + '/for-sale?size=' + size);
  }

  getAnimals(number: number, size: number): Observable<getAnimals> {
    return this.http.get<getAnimals>(environment.path.animals + '?page=' + number + '&size=' + size);
  }

  deleteAnimal(id: number): Observable<any> {
    return this.http.delete(environment.path.animals + '/' + id);
  }

  addAnimal(formData: FormData): Observable<any> {
    return this.http.post(environment.path.animals, formData);
  }

  updateReservationStatus(id: number, formData: FormData): Observable<any> {
    return this.http.put(environment.path.animals + "/" + id, formData);

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
