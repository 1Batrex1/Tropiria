import {Component, Input, input} from '@angular/core';
import {Animal} from "../../../entities/animal";
import {PhotoComponent} from "../../photo/photo.component";

@Component({
  selector: 'app-landing-page-animal',
  standalone: true,
  imports: [
    PhotoComponent
  ],
  templateUrl: './landing-page-animal.component.html',
  styleUrl: './landing-page-animal.component.css'
})
export class LandingPageAnimalComponent {

  @Input() animal?: Animal;

  protected mainPhoto? : string

  private otherPhotos? : string[]

  constructor() { }

  ngOnInit() {
    this.mainPhoto = this.animal?.photos[0].photoName
    this.otherPhotos = this.animal?.photos.slice(1).map(photo => photo.photoName)
  }

}
