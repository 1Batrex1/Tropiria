import {Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {AdminService} from "../../services/admin.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private adminService: AdminService) {
  }

  loginForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  });

  onSubmit() {
    this.adminService.login(this.loginForm.value);
  }
}


