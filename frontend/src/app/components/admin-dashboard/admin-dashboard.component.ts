import { Component } from '@angular/core';
import {Animal} from "../../entities/animal";
import {AnimalService} from "../../services/animal.service";
import {MessageHandlerService} from "../../services/message-handler.service";
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef,
  MatRow, MatRowDef,
  MatTable
} from "@angular/material/table";
import {NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [
    MatTable,
    MatHeaderCellDef,
    MatColumnDef,
    MatHeaderCell,
    MatCell,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatHeaderRowDef,
    MatCellDef,
    NgForOf,
    NgIf,
    RouterLink,
    MatButton
  ],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {

  displayedColumns: string[] = ['id', 'name', 'species','morph','forSale','reservationStatus','price','actions'];


  animals :Animal[] = [];

  number : number = 0;

  size : number = 20;

  totalPages : number = 0;

  totalElements : number = 0;

  constructor(private animalService: AnimalService, private messageHandler: MessageHandlerService) {
  }

  ngOnInit() {
    this.getAnimals();
  }

  getAnimals() {
    this.animalService.getAnimals(this.number,this.size).subscribe(
      {
        next: animals => {
          this.animals = animals.content;
          this.totalElements = animals.totalElements;
          this.totalPages = animals.totalPages;
        },
        error: e => this.messageHandler.handleHttpError(e)
      });
  }

  deleteAnimal(id: number) {
    this.animalService.deleteAnimal(id).subscribe(
      {
        next: () => {
          this.getAnimals();
          this.messageHandler.successInfo('Animal deleted');
        },
        error: e => this.messageHandler.handleHttpError(e)
      }
    )
  }

}



