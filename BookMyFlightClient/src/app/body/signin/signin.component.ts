import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AppService } from '../../app.service';
import { MessageService } from '../../message.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  fail: boolean = false;
  message: string = '';

  constructor(private service: AppService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.messageService.getMessage().subscribe(message => {
      this.message = message;
    });
  }

  signin(form: NgForm) {
    if (form.valid) {
      const username = form.value.username;
      const password = form.value.password;

      this.service.signin(username, password);

      if(!this.service.isAuthenticated()) {
        setTimeout(() => {
          this.fail = true;
        }, 500);
      }
    }
  }
}
