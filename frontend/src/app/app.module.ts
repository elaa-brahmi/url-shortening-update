import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatSidenavModule} from '@angular/material/sidenav';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { LinksComponent } from './pages/links/links.component';
import { CustomLinksComponent } from './pages/custom-links/custom-links.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RouterModule, Routes } from '@angular/router';
import { FooterComponent } from './components/footer/footer.component';
import { QRCodeModule } from 'angularx-qrcode';
import { UrlShorteningApIsService } from './generatedServices/services/url-shortening-ap-is.service';
import { ToastrModule } from 'ngx-toastr';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CreateCustomComponent } from './pages/create-custom/create-custom.component';
import { UpdateComponent } from './pages/update/update.component';
import { LoginComponent } from './components/login/login.component';

const appRoutes: Routes = [
  // Define your routes here, for example:
  // { path: '', component: MainPageComponent },
];




@NgModule({
  declarations: [

    AppComponent,
    MainPageComponent,
    LinksComponent,
    CustomLinksComponent,
    SidebarComponent,
    DashboardComponent,
    FooterComponent,
    CreateCustomComponent,
    UpdateComponent,
    LoginComponent

  ],
  exports: [RouterModule],
  imports: [
    MatExpansionModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    // Notyf removed from imports
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
     // ToastrModule added
     HttpClientModule, // super important
    QRCodeModule,
    BrowserModule,
    MatSidenavModule,
    RouterModule.forRoot(appRoutes), //This sets up the router with your defined routes and provides routing services (like Router, ActivatedRoute, etc.) to your whole app.
    AppRoutingModule,

  ],
  providers: [

    UrlShorteningApIsService,
    HttpClient,
    MatSnackBar

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
