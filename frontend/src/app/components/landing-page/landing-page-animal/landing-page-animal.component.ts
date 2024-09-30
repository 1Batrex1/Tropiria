import {Component, Input} from '@angular/core';
import {Animal} from "../../../entities/animal";
import {PhotoComponent} from "../../photo/photo.component";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {PhotoCountPipe} from "../../../pipe/photo-count.pipe";
import {MorphPipe} from "../../../pipe/morph.pipe";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-landing-page-animal',
  standalone: true,
  imports: [
    PhotoComponent,
    NgClass,
    NgForOf,
    NgIf,
    PhotoCountPipe,
    MorphPipe,
    MatButton
  ],
  templateUrl: './landing-page-animal.component.html',
  styleUrl: './landing-page-animal.component.css'
})
export class LandingPageAnimalComponent {

  @Input() animal?: Animal;

  protected mainPhoto? : string

  protected otherPhotos : string[] = []

  constructor() { }

  ngOnInit() {
    this.mainPhoto = this.animal?.photoList[0].photoName
    this.otherPhotos.push(...this.animal?.photoList.slice(1).map(photo => photo.photoName) || [])
  }

}
