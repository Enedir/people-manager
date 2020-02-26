import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReactiveFormsModule } from '@angular/forms';

import { MaterialModule } from './material/material.module';
import { CpfCnpjPipe } from './cpf/cpf-cnpj.pipe';

@NgModule({
  declarations:
  [
    CpfCnpjPipe,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MaterialModule,

  ],
  exports:[
    ReactiveFormsModule,
    MaterialModule,
    CpfCnpjPipe,
  ]
})
export class SharedModule { }
