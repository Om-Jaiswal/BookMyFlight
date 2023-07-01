import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from '../../app.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  profileForm: FormGroup;
  initialFormValues: any;
  updated: boolean = false;

  constructor(private formBuilder: FormBuilder, private service: AppService) {
    const details = this.service.getDetails();

    this.profileForm = this.formBuilder.group({
      name: [details?.name || '', [Validators.required, Validators.pattern('^[A-Za-z]+\\s[A-Za-z]+$')]],
      mobile: [details?.mobile || '', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: [details?.email || '', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$')]],
      address: [details?.address || '', [Validators.required]]
    });

    this.initialFormValues = this.profileForm.value;
  }

  isFormDisabled(): boolean {
    return JSON.stringify(this.profileForm.value) === JSON.stringify(this.initialFormValues);
  }

  updateProfile() {
    if (this.profileForm.valid) {
      const name = this.profileForm.value.name;
      const mobile = this.profileForm.value.mobile;
      const email = this.profileForm.value.email;
      const address = this.profileForm.value.address;

      const details = {name: name, mobile: mobile, email: email, address: address};

      this.service.update(details).subscribe(
        (response: string) => {
          this.service.setDetails(details);
          this.updated = true;
          console.log(response);
        },
        (error) => {
          console.error('Update Failed:', error);
        }
      );

    } else {
      console.log('Invalid Form!');
    }
  }
  
}
