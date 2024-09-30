import {Species} from "./species";
import {Photo} from "./photo";
import {Morph} from "./morph";
import {AnimalForSale} from "./animal-for-sale";

export class Animal {


  constructor(public id: number = 0,
              public name?: string,
              public description?: string,
              public sex?: number,
              public dateOfBirth?: string,
              public species?: Species,
              public morphs?: Morph[],
              public photoList: Photo[] = [],
              public animalForSale?: AnimalForSale
              )
  {}
}
