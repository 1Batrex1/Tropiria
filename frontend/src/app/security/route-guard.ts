import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {User} from "../entities/user";
import {Injectable} from "@angular/core";
import {BrowserStorageService} from "../services/browser-storage.service";


@Injectable({
  providedIn: 'root'
})
export class RouteGuard {

  user = new User();

  constructor(private router: Router,private browserStorage:BrowserStorageService){

  }

  canActivate(route:ActivatedRouteSnapshot, state:RouterStateSnapshot){
    if(this.browserStorage.getItem('userdetails')){
      this.user = JSON.parse(this.browserStorage.getItem('userdetails')!);
    }
    if(this.user.active === undefined){
      return this.router.createUrlTree(["/login"]);
    }
    return this.user;
  }
}
