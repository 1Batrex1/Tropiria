import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BrowserStorageService} from "./browser-storage.service";
import {Router} from "@angular/router";
import {ErrorHandlerService} from './error-handler.service';
import {Observable} from "rxjs";
import {SessionStorageService} from "./session-storage.service";
import {environment} from "../../constants/environment";

@Injectable({
  providedIn: 'root'
})
export class AdminService {


  constructor(private router: Router, private http: HttpClient, private sessionStorage: SessionStorageService, private errorHandler: ErrorHandlerService) {
  }


  login(credentials: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get(environment.path.login, { headers: headers, observe: 'response' }).subscribe(
      {
        next: (response) => {
          const authHeader = response.headers.get('authorization');
          if (authHeader != null) {
            this.sessionStorage.setItem(environment.jwtToken, authHeader);
            this.router.navigate(['/admin']);
          }
        },
        error: (e) => {
          this.errorHandler.handleHttpError(e);
        }
      }
    );
  }
  checkJwtToken(): Observable<boolean> {
    return this.http.get<boolean>(environment.path.checkJwtToken);
  }

  getCsrftoken(): Observable<string> {
    return this.http.get<string>(environment.path.csrfToken);
  }


}
