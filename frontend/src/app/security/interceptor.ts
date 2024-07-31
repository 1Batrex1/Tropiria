import {HTTP_INTERCEPTORS, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {User} from "../entities/user";
import {BrowserStorageService} from "../services/browser-storage.service";
import {Router} from "@angular/router";

export class Interceptor implements HttpInterceptor{
  user = new User();

  constructor(private router: Router, private browserStorageService: BrowserStorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let httpHeaders = new HttpHeaders();
    if (this.browserStorageService.getItem('userdetails')) {
      this.user = JSON.parse(this.browserStorageService.getItem('userdetails')!);
    }
    if (this.user.password && this.user.login) {
      httpHeaders = httpHeaders.append('Authorization', 'Basic ' + window.btoa(this.user.login + ':' + this.user.password));
    } else {
      let authorization = this.browserStorageService.getItem('Authorization');
      if (authorization) {
        httpHeaders = httpHeaders.append('Authorization', authorization);
      }
    }
    let xsrf = this.browserStorageService.getItem('XSRF-TOKEN');
    if (xsrf) {
      httpHeaders = httpHeaders.append('X-XSRF-TOKEN', xsrf);
    }
    httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');

    let request = req.clone({
      headers: httpHeaders
    });
    return next.handle(request)
  }
}


