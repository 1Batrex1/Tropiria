import {switchMap} from "rxjs";
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";
import { HttpInterceptorFn } from "@angular/common/http";


export const csrfInterceptor: HttpInterceptorFn = (req, next) => {

  const authService = inject(AuthService);

  const csrfMethod = ['POST', 'PUT', 'DELETE', 'PATCH'];


  if (!csrfMethod.includes(req.method)) {
    return next(req);
  } else {
    return authService.getCsrfToken().pipe(
      switchMap((response) => {
        req = req.clone({
            setHeaders: {
              [response.headerName]:response.token,

            },
            withCredentials: true
          }
        );

        return next(req);

      }));
  }
}
