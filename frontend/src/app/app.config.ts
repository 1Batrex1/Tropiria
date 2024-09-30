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
import {provideMomentDateAdapter} from "@angular/material-moment-adapter";
import {IMAGE_CONFIG} from "@angular/common";


export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(withFetch(), withInterceptors([csrfInterceptor, jwtInterceptor])),
    provideToastr(),
    provideAnimations(),
    provideAnimationsAsync(),
    provideMomentDateAdapter({
      parse: {
        dateInput: 'DD/MM/YYYY'
      },
      display: {
        dateInput: 'DD/MM/YYYY',
        monthYearLabel: 'YYYY',
        dateA11yLabel: 'LL',
        monthYearA11yLabel: 'YYYY'
      }
    })
    , {
      provide: IMAGE_CONFIG,
      useValue: {
        breakpoints: [16, 48, 96, 128, 384, 640, 750, 828, 1080, 1200, 1920]
      }
    }

  ]
};
