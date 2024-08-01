import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {User} from "../entities/user";
import {Injectable} from "@angular/core";
import {BrowserStorageService} from "../services/browser-storage.service";
import {AdminService} from "../services/admin.service";
import {Session} from "node:inspector";
import {SessionStorageService} from "../services/session-storage.service";
import {environment} from "../../environment/enviroment";


@Injectable({
  providedIn: 'root'
})
export class RouteGuard {


  private validToken = false;

  constructor(private router: Router, private sessionStorage: SessionStorageService, private adminService: AdminService) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    this.adminService.checkJwtToken().subscribe(
      {
        next: (response) => {
          this.validToken = response;
          if (!this.validToken) {
            this.router.navigate(['/login']);
          }
        },
        error: (e) => {
          this.validToken = false;
          this.router.navigate(['/login']);

        }
      }
    );

  }
}
