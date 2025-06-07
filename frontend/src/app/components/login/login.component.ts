import { Component } from '@angular/core';
import { LoginService } from 'src/app/manualService/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private loginService:LoginService){}
  ngOnInit(){
    this.loginService.login();

  }


}
