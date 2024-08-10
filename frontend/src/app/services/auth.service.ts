import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {BrowserStorageService} from "./browser-storage.service";
import {Router} from "@angular/router";
import {MessageHandlerService} from './message-handler.service';
import {Observable, switchMap} from "rxjs";
import {environment} from "../../constants/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private router: Router, private http: HttpClient, private localStorage: BrowserStorageService, private errorHandler: MessageHandlerService) {
  }

  private adminPath = '/admin';

  private authHeader = 'Authorization';

  login(credentials: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get(environment.path.login, {headers: headers, observe: 'response'}).subscribe(
      {
        next: (response) => {
          const authHeader = response.headers.get(this.authHeader);
          if (authHeader != null) {
            this.localStorage.setItem(environment.jwtToken, authHeader);
            this.router.navigate([this.adminPath]);
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

  getCsrfToken(): Observable<CsrfToken> {
    return this.http.get<CsrfToken>(environment.path.csrfToken)
  }


}

interface CsrfToken {
  token: string;
  headerName: string;
}
