import { Component, ElementRef, ViewChild } from '@angular/core';
import { UrlShorteningApIsService } from 'src/app/generatedServices/services/url-shortening-ap-is.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  @ViewChild('input') input: ElementRef | undefined;
  constructor(private Shortservice:UrlShorteningApIsService,private toastr: ToastrService
    ,private router:Router) {
      // Initialize any properties or services needed for the dashboard
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
