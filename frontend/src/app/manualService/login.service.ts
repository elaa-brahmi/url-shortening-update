import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private basePath = 'http://localhost:8080'; // or from environment.ts
   constructor(private http: HttpClient,private router:Router) {}
   login(): void {
     window.location.href = `${this.basePath}/login/google`;
   }
   logout(){
    //delete token
    localStorage.removeItem('token');
    localStorage.removeItem('name');
    localStorage.removeItem('id');
    this.router.navigate(['/login']);


   }
}
