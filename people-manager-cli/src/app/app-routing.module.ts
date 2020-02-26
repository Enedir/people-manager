import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    children: [
        {
            path: '',
            redirectTo: 'person',
            pathMatch: 'full',
        },
        {
            path: 'person',
            loadChildren: () => import('./features/person/person.module').then(p => p.PersonModule),
            data: {
                breadcrumbOptions: {
                    breadcrumbLabel: 'Pessoas',
                },
            },
        },
    ],
},
    { path: '**', redirectTo: '/' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
