import { Pipe, PipeTransform } from '@angular/core';
import {Morph} from "../entities/morph";

@Pipe({
  name: 'morph',
  standalone: true
})
export class MorphPipe implements PipeTransform {

  transform(value: Morph[] | undefined, ): string {
    if(value !== undefined) {
      return value.map(morph => morph.name).join(', ');
    }
    return '';

  }

}
