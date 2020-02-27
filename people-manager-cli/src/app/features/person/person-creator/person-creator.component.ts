import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { take } from 'rxjs/internal/operators/take';

import { PersonService } from '../shared/person.service';
import { PersonCommandRegister } from '../shared/person.model';

import { CpfCnpjValidators } from './../../../shared/cpf/cpf-validator';
import { ValidateRepeatedCpf } from '../shared/async-cpf.validator';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  templateUrl: './person-creator.component.html',
  styleUrls: ['./person-creator.component.scss']
})
export class PersonCreatorComponent {

  private avatarId: number;

  public form: FormGroup = this.fb.group({
    id: [null],
    name: ['', [Validators.required, Validators.maxLength(150)]],
    email: ['', [Validators.email, Validators.maxLength(400)]],
    cpf: ['', 
            {
              validators: [Validators.required, CpfCnpjValidators.checkCpf],
              asyncValidators: [ValidateRepeatedCpf.checkCpfIsRepeated(this.service)],
              updateOn: 'change'
            }],
    birthDate: ['', Validators.required],
  });

  constructor(private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private service: PersonService,
    public snackBar: MatSnackBar) { }

  onUpdaloadResponse(imageId: number): void {
    this.avatarId = imageId;
  }

  onCreate(): void {
    const command: PersonCommandRegister = Object.assign(new PersonCommandRegister(), this.form.value);
    command.avatarId = this.avatarId;
    
    this.service.post(command)
      .pipe(take(1))
      .subscribe((x: any) => {
        this.snackBar.open('Registro atualizado com sucesso.', 'Fechar', {
          duration: 2000,
          panelClass: ['green-snackbar']
        });

        this.redirect();
      }, (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
         this.snackBar.open('Aconteceu um erro de conexão com o servidor teste mais tarde.', 'Fechar', {
            duration: 2000,
            panelClass: ['blue-snackbar']
          });
        } else {
          this.snackBar.open('Aconteceu um erro: status ->  ' + err.status + 'mensagem de erro -> ' + err.error, 'Fechar', {
            duration: 2000,
            panelClass: ['red-snackbar']
          });
        }
      });
  }

  redirect(): void {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
