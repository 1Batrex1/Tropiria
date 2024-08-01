import {Component, Input} from '@angular/core';
import {PhotoService} from "../../services/photo.service";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {NgIf, NgOptimizedImage} from "@angular/common";
import {environment} from "../../../constants/environment";

@Component({
  selector: 'app-photo',
  standalone: true,
  imports: [
    NgIf,
    NgOptimizedImage
  ],
  templateUrl: './photo.component.html',
  styleUrl: './photo.component.css'
})
export class PhotoComponent {

  @Input() photoName?: string;

  @Input() width: number = 200;

  @Input() height: number = 200;

  photoUrl?: SafeUrl ;

  constructor(private photoService: PhotoService, private sanitizer: DomSanitizer) { }

  ngOnInit()
  {
    if (this.photoName) {
      this.loadPhoto(this.photoName);
    }
  }

  loadPhoto(photoName: string) {
      this.photoUrl = environment.path.photos + "/" + photoName;
  }
}
