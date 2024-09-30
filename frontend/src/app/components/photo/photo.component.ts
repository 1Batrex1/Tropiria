import {Component, Input} from '@angular/core';
import {PhotoService} from "../../services/photo.service";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {NgIf, NgOptimizedImage, NgStyle} from "@angular/common";
import {environment} from "../../../constants/environment";

@Component({
  selector: 'app-photo',
  standalone: true,
  imports: [
    NgIf,
    NgOptimizedImage,
    NgStyle
  ],
  templateUrl: './photo.component.html',
  styleUrl: './photo.component.css'
})
export class PhotoComponent {

  @Input() photoName?: string;

  @Input() width: string = '100%';

  height: string  = 'auto';

  photoUrl?: SafeUrl ;

  constructor(private photoService: PhotoService, private sanitizer: DomSanitizer) { }

  ngOnInit()
  {
    if (this.photoName) {
      this.loadPhoto(this.photoName);
    }
  }

  loadPhoto(photoName: string) {
      this.photoUrl = environment.path.photo + "/" + photoName;
  }
}
