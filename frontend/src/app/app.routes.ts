import {Routes} from '@angular/router';
import {LandingPageComponent} from "./components/landing-page/landing-page.component";
import {TerrariumFormComponent} from "./components/terrarium-form/terrarium-form.component";
import {AdminDashboardComponent} from "./components/admin-dashboard/admin-dashboard.component";
import {RouteGuard} from "./security/route-guard";
import {LoginComponent} from "./components/login/login.component";

export const routes: Routes = [
  {
    path: '',
    component: LandingPageComponent
  },
  {
    path: "order-terrarium",
    component: TerrariumFormComponent
  }
  ,
  {
    path: "admin",
    component: AdminDashboardComponent,
    canActivate: [RouteGuard]
  }
  ,
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "**",
    redirectTo: ''
  }
];
