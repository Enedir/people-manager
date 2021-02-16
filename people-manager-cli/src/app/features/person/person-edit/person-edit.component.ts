import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { take } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';

import { PersonService } from '../shared/person.service';

import { CpfCnpjValidators } from './../../../shared/cpf/cpf-validator';

import { Person, PersonCommandUpdate } from './../shared/person.model';

import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  templateUrl: './person-edit.component.html',
  styleUrls: ['./person-edit.component.scss']
})
export class PersonEditComponent implements OnInit {

  private person : Person;

  constructor(private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private service: PersonService,
    public snackBar: MatSnackBar) { }

    url: string;

    public form: FormGroup = this.fb.group({
      id: [0],
      name: ['', [Validators.required, Validators.maxLength(150)]],
      email: ['', [Validators.email, Validators.maxLength(400)]],
      cpf: ['', [Validators.required, CpfCnpjValidators.checkCpf]],
      birthDate: ['', Validators.required],
    });
  
   
  ngOnInit(): void {
    this.route.data.subscribe(response => {
      this.person = Object.assign(new Person(), response.solicitation);
      console.log(this.person.url);
      this.form.setValue(
        {
          'id': this.person.id,
          'name': this.person.name,
          'email': this.person.email,
          'cpf': this.person.cpf,
          'birthDate': new Date(this.person.birthDate)
        });
        this.url = this.person.url;
    });
  }

  onUpdaloadResponse(imageId: number): void {
    this.person.avatarId = imageId;
  }


  public onEdit(): void {
    const command: PersonCommandUpdate = Object.assign(new PersonCommandUpdate(), this.form.value);
    command.avatarId = this.person.avatarId;
    
    this.service.put(command)
      .pipe(take(1))
      .subscribe(() => {
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

  public redirect(): void {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }


}
