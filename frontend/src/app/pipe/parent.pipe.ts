import { Pipe, PipeTransform } from '@angular/core';
import {Animal} from "../entities/animal";
@Pipe({
  name: 'parent',
  standalone: true
})
export class ParentPipe implements PipeTransform {

  transform(value: Animal[] | null): string {
    if(value !== null) {
      return value.map(animal => animal.name).join(', ');
    }
    return '';
  }

}
