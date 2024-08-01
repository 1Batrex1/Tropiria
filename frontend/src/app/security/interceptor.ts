import {HttpInterceptorFn} from "@angular/common/http";
import {inject} from "@angular/core";
import {SessionStorageService} from "../services/session-storage.service";
import {environment} from "../../constants/environment";

export const interceptor : HttpInterceptorFn = (req, next) => {

  const sessionStorage = inject(SessionStorageService);

  const jwtToken = sessionStorage.getItem(environment.jwtToken);


  if (jwtToken) {
    req = req.clone({
      setHeaders: {
        Authorization: jwtToken
      }
    });
  }

  return next(req);
}
