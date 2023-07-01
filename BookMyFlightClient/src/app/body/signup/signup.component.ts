import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from '../../app.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  signupForm: FormGroup;
  same: boolean = false;

  constructor(private formBuilder: FormBuilder, private service: AppService) {
    this.signupForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern('^[A-Za-z]+\\s[A-Za-z]+$')]],
      username: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]{6,}$')]],
      email: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$')]],
      mobile: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      password: ['', [Validators.required]],
      rePassword: ['', [Validators.required]]
    });
  }

  signup() {
    if (this.signupForm.valid) {
      const name = this.signupForm.value.name;
      const username = this.signupForm.value.username;
      const email = this.signupForm.value.email;
      const mobile = this.signupForm.value.mobile;
      const password = this.signupForm.value.password;
      const rePassword = this.signupForm.value.rePassword;

      if (password === rePassword) {
        this.service.signup(name, username, email, mobile, password).subscribe(
          (response: string) => {
            console.log(response);
          },
          (error) => {
            console.error('Signup Failed:', error);
          }
        );
      } else {
        this.same = true;
      } 
    } 
  }

}
