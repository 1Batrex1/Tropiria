import {HttpInterceptorFn} from "@angular/common/http";
import {inject} from "@angular/core";
import {environment} from "../../constants/environment";
import {BrowserStorageService} from "../services/browser-storage.service";

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {



  const localStorage = inject(BrowserStorageService);

  const jwtToken = localStorage.getItem(environment.jwtToken);


  if (jwtToken) {
    req = req.clone({
      setHeaders: {
        Authorization: jwtToken
      },
      withCredentials: true
    });
  }

  return next(req);

}
