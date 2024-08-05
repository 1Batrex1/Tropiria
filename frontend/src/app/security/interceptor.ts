import {HttpInterceptorFn} from "@angular/common/http";
import {inject} from "@angular/core";
import {environment} from "../../constants/environment";
import {BrowserStorageService} from "../services/browser-storage.service";
import {AdminService} from "../services/admin.service";
import {ErrorHandlerService} from "../services/error-handler.service";

export const interceptor : HttpInterceptorFn = (req, next) => {



  const csrfAllowedMethods = ['POST', 'PUT', 'DELETE', 'PATCH'];

  const csrfHeader = 'X-XSRF-TOKEN';

  const localStorage = inject(BrowserStorageService);

  const adminService = inject(AdminService);

  const errorHandler = inject(ErrorHandlerService);

  const jwtToken = localStorage.getItem(environment.jwtToken);


  if (csrfAllowedMethods.includes(req.method)) {
    adminService.getCsrftoken().subscribe(
      {
        next: (response) => {
          req = req.clone({
            setHeaders: {
              csrfHeader: response
            }
          });
        },
        error: (e) => {
          errorHandler.handleHttpError(e);
        },
        complete: () => {
          if (jwtToken) {
            req = req.clone({
              setHeaders: {
                Authorization: jwtToken
              }
            });
          }
          return next(req);
        }
  });}
  else if (jwtToken) {
    req = req.clone({
      setHeaders: {
        Authorization: jwtToken
      }
    });
  }

  return next(req);
}
