import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PersonListComponent } from './person-list/person-list.component';
import { PersonCreatorComponent } from './person-creator/person-creator.component';
import { PersonEditComponent } from './person-edit/person-edit.component';

import { PersonResolveService } from './shared/person.service';

const routes: Routes = [
  {
    path: '',
    component: PersonListComponent,
},
{
    path: 'create',
    component: PersonCreatorComponent,
},
{
    path: 'edit/:personId',
    component: PersonEditComponent,
    resolve: {
        solicitation: PersonResolveService,
    }
},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonRoutingModule { }
