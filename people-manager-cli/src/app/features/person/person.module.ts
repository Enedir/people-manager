import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { SharedModule } from './../../shared/shared.module';

import { PersonRoutingModule } from './person-routing.module';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonFormComponent } from './person-form/person-form.component';
import { PersonCreatorComponent } from './person-creator/person-creator.component';
import { PersonEditComponent } from './person-edit/person-edit.component';

import { PersonService, PersonResolveService } from './shared/person.service';


@NgModule({
  declarations: [
    PersonListComponent, 
    PersonFormComponent, 
    PersonCreatorComponent, 
    PersonEditComponent
  ],
  imports: [
    CommonModule,
    PersonRoutingModule,
    HttpClientModule,
    SharedModule,
  ],
  providers: [
    PersonService,
    PersonResolveService
  ]
})
export class PersonModule { }
