import {Component} from '@angular/core';
import {Animal} from "../../entities/animal";
import {AnimalService} from "../../services/animal.service";
import {ErrorHandlerService} from "../../services/error-handler.service";
import {NgForOf, NgOptimizedImage} from "@angular/common";
import {PhotoComponent} from "../photo/photo.component";
import {RouterLink} from "@angular/router";
import {LandingPageAnimalComponent} from "./landing-page-animal/landing-page-animal.component";
import {MatSlideToggle} from "@angular/material/slide-toggle";

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [
    NgForOf,
    NgOptimizedImage,
    PhotoComponent,
    RouterLink,
    LandingPageAnimalComponent,
    MatSlideToggle
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {

  animalsForSale: Animal[] = [];

  constructor(private animalService: AnimalService, private errorHandler: ErrorHandlerService) {
  }

  ngOnInit() {
    this.animalService.getAnimalsForSale().subscribe(
      {
        next: animals => {
          this.animalsForSale = animals.content
        },
        error: e => this.errorHandler.handleHttpError(e)
      });
  }
}


