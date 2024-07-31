import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environment/enviroment";
import {BrowserStorageService} from "./browser-storage.service";
import {Router} from "@angular/router";
import { ErrorHandlerService } from './error-handler.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private router: Router, private http: HttpClient, private browserStorage: BrowserStorageService, private errorHandler: ErrorHandlerService) {
  }


  login(credentials: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get(environment.login, {headers: headers}).subscribe(
      {
        next: (response) => {
          this.browserStorage.setItem('jwt', JSON.stringify(response));
          this.router.navigate(['/admin']);
        },
        error: (e)  => {
          this.errorHandler.handleHttpError(e);
        }

      });
  }
}
