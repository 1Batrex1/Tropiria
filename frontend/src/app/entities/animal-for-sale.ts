import {Animal} from "./animal";

export class AnimalForSale {
  constructor(
    public price: number,
    public reservationStatus: string,
    public parents?: Animal[]
  ){}
}
