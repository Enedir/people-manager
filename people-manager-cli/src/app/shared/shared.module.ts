import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { MaterialModule } from './material/material.module';
import { CpfCnpjPipe } from './cpf/cpf-cnpj.pipe';
import { FileModule } from './file/file.module';

@NgModule({
  declarations:
  [
    CpfCnpjPipe,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    FileModule,

  ],
  exports:[
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    CpfCnpjPipe,
    FileModule,
  ]
})
export class SharedModule { }
