import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {BrowserStorageService} from "../services/browser-storage.service";
import {environment} from "../../constants/environment";


@Injectable({
  providedIn: 'root'
})
export class RouteGuard {

  private loginPath = '/login';

  private validToken = false;

  constructor(private router: Router,private localStorage : BrowserStorageService, private adminService: AuthService) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    this.adminService.checkJwtToken().subscribe(
      {
        next: (response) => {
          this.validToken = response;
          if (!this.validToken) {
            this.router.navigate([this.loginPath]);
          }
        },
        error: (e) => {
          this.localStorage.removeItem(environment.jwtToken);
          this.router.navigate([this.loginPath]);

        }
      }
    );

  }
}
