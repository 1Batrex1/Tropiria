import {Component} from '@angular/core';
import {Animal} from "../../entities/animal";
import {AnimalService} from "../../services/animal.service";
import {MessageHandlerService} from "../../services/message-handler.service";
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {PhotoComponent} from "../photo/photo.component";
import {RouterLink} from "@angular/router";
import {MatSlideToggle} from "@angular/material/slide-toggle";
import {MatGridList, MatGridTile} from "@angular/material/grid-list";
import {MatCard, MatCardActions, MatCardContent, MatCardHeader, MatCardImage} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {NavItemComponent} from "./nav-item/nav-item.component";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [
    NgForOf,
    NgOptimizedImage,
    PhotoComponent,
    RouterLink,
    MatSlideToggle,
    NgIf,
    MatGridList,
    MatGridTile,
    MatCard,
    MatCardHeader,
    MatCardImage,
    MatCardContent,
    MatCardActions,
    MatButton,
    NavItemComponent,
    MatIcon
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {

  animalsForSale: Animal[] = [];

  animalForLandingPageSize: number = 3;

  constructor(private animalService: AnimalService, private messageHandler: MessageHandlerService) {
  }

  ngOnInit() {
    this.animalService.getAnimalsForSale(this.animalForLandingPageSize).subscribe(
      {
        next: animals => {
          this.animalsForSale = animals.content
        },
        error: e => this.messageHandler.handleHttpError(e)
      });
  }
}


