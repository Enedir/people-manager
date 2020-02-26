import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { take } from 'rxjs/internal/operators/take';
import { Subject } from 'rxjs';

import { PersonService } from '../shared/person.service';

import { CpfCnpjValidators } from './../../../shared/cpf/cpf-validator';

import { Person, PersonCommandUpdate } from './../shared/person.model';
import { takeUntil } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  templateUrl: './person-edit.component.html',
  styleUrls: ['./person-edit.component.scss']
})
export class PersonEditComponent implements OnInit {

  constructor(private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private service: PersonService) { }

    public form: FormGroup = this.fb.group({
      id: [0],
      name: ['', [Validators.required, Validators.maxLength(150)]],
      email: ['', [Validators.email, Validators.maxLength(400)]],
      cpf: ['', [Validators.required, CpfCnpjValidators.checkCpf]],
      birthDate: ['', Validators.required],
    });
  
   
  ngOnInit(): void {
    this.route.data.subscribe(response => {
      const person = Object.assign(new Person(), response.solicitation);
      this.form.setValue(
        {
          'id': person.id,
          'name': person.name,
          'email': person.email,
          'cpf': person.cpf,
          'birthDate': new Date(person.birthDate)
        });

    });
  }

  public onEdit(): void {
    const command: PersonCommandUpdate = Object.assign(new PersonCommandUpdate(), this.form.value);
    this.service.put(command)
      .pipe(take(1))
      .subscribe(() => {
        this.redirect();
      }, (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred.
          // this.spinner.hide();
          alert('Aconteceu um erro:' + err.error.message);
        } else {
          // Backend returns unsuccessful response codes such as 404, 500 etc.
          alert('Aconteceu um erro: status ->  ' + err.status + 'mensagem de erro -> ' + err.error);
          // this.spinner.hide();
          // Log errors if any
        }
      });
  }

  public redirect(): void {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }


}
