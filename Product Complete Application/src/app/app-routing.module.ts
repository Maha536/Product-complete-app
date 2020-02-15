import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { SearchResultComponent } from './search-result/search-result.component';

import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGuardService } from './services/auth-guard.service';
import { ErrorComponent } from './error/error.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductAddComponent } from './product/product-add/product-add.component';
import { ProductUpdateComponent } from './product/product-update/product-update.component';


// array of Route objects
// each route object holds the relation between url and component
const routes: Routes = [
    // Route object
    { path:"", redirectTo : "home", pathMatch: "full"},
    { path:"home", component: HomeComponent},
    { path:"contactus", component: ContactComponent},
    { path:"aboutus", component: AboutComponent},
    { path:"login", component: LoginComponent},

    // connect the guard service
    { path:"product", component: ProductListComponent, canActivate : [AuthGuardService]},
    { path:"product-add", component: ProductAddComponent, canActivate : [AuthGuardService]},
    { path:"product-update/:productId", component: ProductUpdateComponent, canActivate : [AuthGuardService]},
    { path:"logout", component: LogoutComponent, canActivate : [AuthGuardService]},
    // <url>/:<name by which data is accessed>
    { path:"search/:searchText", component: SearchResultComponent},

    // add a fallback mapping
    { path:"**", component: ErrorComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)], // registering routes
  exports: [RouterModule]
})
export class AppRoutingModule { }
