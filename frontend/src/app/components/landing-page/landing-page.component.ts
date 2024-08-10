import {Component} from '@angular/core';
import {Animal} from "../../entities/animal";
import {AnimalService} from "../../services/animal.service";
import {MessageHandlerService} from "../../services/message-handler.service";
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
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
    MatSlideToggle,
    NgIf
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {

  animalsForSale: Animal[] = [];

  constructor(private animalService: AnimalService, private messageHandler: MessageHandlerService) {
  }

  ngOnInit() {
    this.animalService.getAnimalsForSale().subscribe(
      {
        next: animals => {
          this.animalsForSale = animals.content
        },
        error: e => this.messageHandler.handleHttpError(e)
      });
  }
}


