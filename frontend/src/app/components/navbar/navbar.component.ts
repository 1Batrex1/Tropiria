import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {NavbarButtonComponent} from "./navbar-button/navbar-button.component";
import {NgOptimizedImage} from "@angular/common";
import {PhotoComponent} from "../photo/photo.component";
import {BrowserStorageService} from "../../services/browser-storage.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterLink,
    NavbarButtonComponent,
    NgOptimizedImage,
    PhotoComponent
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(protected browserStorage : BrowserStorageService) {  }

  private logo = "logo.png";
}
