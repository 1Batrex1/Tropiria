import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sex',
  standalone: true
})
export class SexPipe implements PipeTransform {

  transform(value: number): string {
    switch (value) {
      case 0:
        return 'Nieznana';
      case 1:
        return 'Samiec';
      case 2:
        return 'Samica';
      default:
        return 'Nieznana';
    }
  }

}
