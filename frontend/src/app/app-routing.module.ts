import { createComponent, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { LinksComponent } from './pages/links/links.component';
import { CustomLinksComponent } from './pages/custom-links/custom-links.component';
import { CreateCustomComponent } from './pages/create-custom/create-custom.component';
import { UpdateComponent } from './pages/update/update.component';

const routes: Routes = [
  {path :'main-page', component:MainPageComponent},
  {path :'links', component:LinksComponent},
  {path :'customLinks', component:CustomLinksComponent},
  {path:'createCustom',component:CreateCustomComponent},
  {path:'update-link/:id',component:UpdateComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
