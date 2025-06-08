import { Component, ElementRef, ViewChild } from '@angular/core';
import { UrlShorteningApIsService } from 'src/app/generatedServices/services/url-shortening-ap-is.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/manualService/login.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  name:String |null= '';
  loggedIn:boolean=false;
  @ViewChild('input') input: ElementRef | undefined;
  constructor(private Shortservice:UrlShorteningApIsService,private toastr: ToastrService
    ,private router:Router,private route:ActivatedRoute,private loginService:LoginService) {
      // Initialize any properties or services needed for the dashboard
  }
  ngOnInit(){
     this.route.queryParams.subscribe(params=>{
      const token=params['token'];
      const name=params['fullName'];
      const id=params['id'];
      console.log("token and name ",token," ",name)
      if(token){
        localStorage.setItem('token',token);
        localStorage.setItem('name',name);
        localStorage.setItem('id',id);

        this.router.navigate(['/main-page']);

      }
  });
    if(localStorage.getItem('token') && localStorage.getItem('name')){
      this.loggedIn=true;
       this.name = localStorage.getItem('name');

      console.log("user logged in");
    }
    else{
      this.loggedIn=false;
      console.log("user logged out");
    }
  }
  login(){
    this.router.navigate(['/login']);

  }
  logout(){
    this.loggedIn=false;
    this.loginService.logout();
    //delete token
  }
  shorten(): void {
    // Implement the URL shortening logic here
    if (this.input) {
      console.log("url entered ",this.input.nativeElement.value);
      const url = this.input.nativeElement.value;
      const urlRequest = {
        originalUrl: url
      };

      const params = {
        body: urlRequest
      };
      this.Shortservice.createUrl$Response(params).subscribe(
              (response) => {
                console.log("shortened url ",response);
                this.toastr.success('URL shortened successfully!', 'Success');
                this.router.navigate(['/links', response]);

              },
              (error) => {
                console.error('Error shortening URL:', error);
                if(error.error){
                  const errorObject = JSON.parse(error.error);
                  console.log('Error object:', errorObject);
                  if(errorObject.message){
                  const errorMessage = errorObject.message;
                  console.log('Error message:', errorMessage);
                  this.toastr.error(errorMessage, 'Error');
                }
                else if(errorObject.originalUrl){
                  const errorMessage = errorObject.originalUrl;
                  console.log('Error message:', errorMessage);
                  this.toastr.error(errorMessage, 'Error');
                }}

              }
            );

          }
        }

}
