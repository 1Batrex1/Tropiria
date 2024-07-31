import {Component, Input} from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-navbar-button',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './navbar-button.component.html',
  styleUrl: './navbar-button.component.css',
})
export class NavbarButtonComponent {

  @Input() text: string = "";
  @Input() link: string = "";

}
