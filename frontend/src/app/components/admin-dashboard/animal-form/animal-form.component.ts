import {Component} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Species} from "../../../entities/species";
import {Morph} from "../../../entities/morph";
import {Animal} from "../../../entities/animal";
import {AnimalService} from "../../../services/animal.service";
import {SpeciesService} from "../../../services/species.service";
import {MorphService} from "../../../services/morph.service";
import {MessageHandlerService} from "../../../services/message-handler.service";
import {MatStep, MatStepLabel, MatStepper, MatStepperNext, MatStepperPrevious} from "@angular/material/stepper";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect, MatSelectChange} from "@angular/material/select";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle, MatDateRangePicker} from "@angular/material/datepicker";
import {MatIcon} from "@angular/material/icon";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatCheckbox} from "@angular/material/checkbox";
import {MatInput} from "@angular/material/input";
import {MatTextColumn} from "@angular/material/table";
import {DatePipe, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {CdkDrag, CdkDragHandle, CdkDropList} from "@angular/cdk/drag-drop";
import {MatCard} from "@angular/material/card";
import {CdkTextareaAutosize} from "@angular/cdk/text-field";
import {SexPipe} from "../../../pipe/sex.pipe";
import {MorphPipe} from "../../../pipe/morph.pipe";
import {ParentPipe} from "../../../pipe/parent.pipe";
import moment from "moment";
import {Router, RouterLink} from "@angular/router";
import {reservationStatus} from "../../../../constants/reservationConstant";

@Component({
  selector: 'app-animal-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatStepper,
    MatStep,
    MatLabel,
    MatFormField,
    MatOption,
    MatSelect,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatDatepicker,
    MatIcon,
    MatButton,
    MatStepperPrevious,
    MatIconButton,
    MatCheckbox,
    MatInput,
    MatStepperNext,
    MatStepLabel,
    MatTextColumn,
    NgForOf,
    MatDateRangePicker,
    MatSuffix,
    CdkDropList,
    CdkDragHandle,
    CdkDrag,
    NgIf,
    MatCard,
    CdkTextareaAutosize,
    DatePipe,
    SexPipe,
    MorphPipe,
    NgOptimizedImage,
    ParentPipe,
    RouterLink,
  ],
  templateUrl: './animal-form.component.html',
  styleUrl: './animal-form.component.css'
})
export class AnimalFormComponent {
  isDragOver = false;
  animalFormFirstGroup: FormGroup;
  animalFormSecondGroup: FormGroup;
  animalFormThirdGroup: FormGroup;
  animalFormFourthGroup: FormGroup;
  avalibleSpecies: Species[] = [];
  avalibleMorphs: Morph[] = [];
  avalibleParents: Animal[] = [];
  avalibleSex = {
    unknown: 0,
    male: 1,
    female: 2
  }



  constructor(private router: Router,
              private animalService: AnimalService,
              private speciesService: SpeciesService,
              private morphService: MorphService,
              private fb: FormBuilder,
              private messageHandler: MessageHandlerService) {
    this.getMorphs()
    this.getSpecies()
    this.getParents()

    this.animalFormFirstGroup = this.fb.group({
      name: ['Gekon', Validators.required],
      description: "",
    });

    this.animalFormSecondGroup = this.fb.group({
      sex: [0, Validators.required],
      dateOfBirth: [new Date(), Validators.required],
      species: ['', Validators.required],
      morphs: [[], Validators.required]
    });

    this.animalFormThirdGroup = this.fb.group({
      photoList: this.fb.array([], Validators.required),
    });
    this.animalFormFourthGroup = this.fb.group({
      isForSale: [false, Validators.required],
      parents: [],
      price: [0, Validators.required],
      reservationStatus: [reservationStatus.for_sale, Validators.required]
    });
  }


  get isForSale() {
    return this.animalFormFourthGroup.get('isForSale')?.value;
  }

  get photoList(): FormArray {
    return this.animalFormThirdGroup.get('photoList') as FormArray;
  }

  get parents() {
    return this.animalFormFourthGroup.get('parents') as FormArray;
  }


  getSpecies() {
    this.speciesService.getSpecies().subscribe(
      {
        next: species => this.avalibleSpecies = species,
        error: e => this.messageHandler.handleHttpError(e)
      }
    );
  }

  getMorphs() {
    this.morphService.gerMorphs().subscribe(
      {
        next: morphs => this.avalibleMorphs = morphs,
        error: e => this.messageHandler.handleHttpError(e)
      }
    );
  }

  getParents() {
    this.animalService.getParents().subscribe(
      {
        next: animals => this.avalibleParents = animals,
        error: e => this.messageHandler.handleHttpError(e)
      }
    );
  }

  removePhoto(index: number): void {
    this.photoList.removeAt(index);
  }

  onFileSelect(event: any): void {
    const files = event.target.files;
    if (files?.length) {
      const remainingSlots = 5 - this.photoList.length;
      const filesToAdd = Array.from(files).slice(0, remainingSlots) as File[];
      this.addFilesToFormArray(filesToAdd);
    }
  }

  onDrop(event: DragEvent): void {
    event.preventDefault();
    event.stopPropagation();
    this.isDragOver = false;

    const files = event.dataTransfer?.files;
    if (files?.length) {
      const remainingSlots = 5 - this.photoList.length;
      const filesToAdd = Array.from(files).slice(0, remainingSlots);
      this.addFilesToFormArray(filesToAdd);
    }
  }

  onDragOver(event: DragEvent): void {
    event.preventDefault();
    this.isDragOver = true;
  }

  onDragLeave(event: DragEvent): void {
    event.preventDefault();
    this.isDragOver = false;
  }

  submit() {
    let animal = new Animal();
    animal.name = this.animalFormFirstGroup.get('name')?.value;
    animal.description = this.animalFormFirstGroup.get('description')?.value;
    animal.sex = this.animalFormSecondGroup.get('sex')?.value;
    animal.dateOfBirth = moment(this.animalFormSecondGroup.get('dateOfBirth')?.value).format('DD-MM-YYYY');
    animal.species = this.animalFormSecondGroup.get('species')?.value;
    animal.morphs = this.animalFormSecondGroup.get('morphs')?.value;
    animal.photoList = [];
    if (this.isForSale)
      animal.animalForSale = {
        price: this.animalFormFourthGroup.get('price')?.value,
        reservationStatus: this.animalFormFourthGroup.get('reservationStatus')?.value,
        parents: this.parents.value
      }
    let photos: File[] = this.photoList.controls.map(control => control.get('file')?.value);
    let formData = new FormData();
    formData.append('animal', JSON.stringify(animal));
    photos.forEach(photo => formData.append('photoList', photo));

    this.animalService.addAnimal(formData).subscribe(
      {
        next: () => {
          this.messageHandler.successInfo("Dodano zwierzę")
          this.router.navigate(['/admin'])
        },
        error: e => this.messageHandler.handleHttpError(e)
      }
    );
  }

  onSelectionChange(event: MatSelectChange) {
    if (this.parents.length > 2) {
      this.parents.removeAt(this.parents.length - 1);
    }
  }


  addFilesToFormArray(files: File[]): void {
    files.slice(0, 5 - this.photoList.length).forEach(file => {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.photoList.push(this.fb.group({
          file: file,
          url: e.target.result
        }));
      };
      reader.readAsDataURL(file);
    });
  }
  protected readonly reservationStatus = reservationStatus;
}
