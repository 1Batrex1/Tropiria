import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'photoCount',
  standalone: true
})
export class PhotoCountPipe implements PipeTransform {

  transform(value: number): string {
    switch (value) {
      case 1:
        return '18vw';
      case 2:
        return '10vw';
      case 3:
        return '10vw';
      case 4:
        return '10vw';
      default:
        return '5vw';
    }
  }

}
