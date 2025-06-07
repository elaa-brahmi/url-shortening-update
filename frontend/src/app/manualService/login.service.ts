import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private basePath = 'http://localhost:8080'; // or from environment.ts

   constructor(private http: HttpClient) {}

   login(): void {
     window.location.href = `${this.basePath}/login/google`;
     //const params = new HttpParams().set('shortUrl', shortUrl); // for @requestParam
   }
}
