import {Species} from "./species";
import {Photos} from "./photos";
import {Morph} from "./morph";
import {AnimalForSale} from "./animal-for-sale";

export class Animal {


  constructor(public id: number,
              public name: string,
              public description: string,
              public sex: number,
              public dateOfBirth: string,
              public species: Species,
              public morphs: Morph[],
              public photos: Photos[],
              public animalForSale: AnimalForSale
              )
  {}
}
