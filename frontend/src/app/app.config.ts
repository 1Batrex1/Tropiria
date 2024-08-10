import {ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideClientHydration} from '@angular/platform-browser';
import {provideHttpClient, withFetch, withInterceptors} from "@angular/common/http";
import {provideToastr} from "ngx-toastr";
import {provideAnimations} from "@angular/platform-browser/animations";
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {jwtInterceptor} from "./security/jwtInterceptor";
import {csrfInterceptor} from "./security/csrfInterceptor";
import {provideNativeDateAdapter} from "@angular/material/core";

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(withFetch(),withInterceptors([csrfInterceptor,jwtInterceptor])),
    provideToastr(),
    provideAnimations(),
    provideAnimationsAsync(),
    provideNativeDateAdapter()
  ]
};
