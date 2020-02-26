import { CpfCnpjValidators } from './../../../shared/cpf/cpf-validator';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { take } from 'rxjs/internal/operators/take';

import { PersonService } from '../shared/person.service';
import { PersonCommandRegister } from '../shared/person.model';

@Component({
  templateUrl: './person-creator.component.html',
  styleUrls: ['./person-creator.component.scss']
})
export class PersonCreatorComponent {

  public form: FormGroup = this.fb.group({
    id: [null],
    name: ['', [Validators.required, Validators.maxLength(150)]],
    email: ['', [Validators.email, Validators.maxLength(400)]],
    cpf: ['', [Validators.required, CpfCnpjValidators.checkCpf]],
    birthDate: ['', Validators.required],
  });

  constructor(private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private service: PersonService) { }

  onCreate(): void {
    const command: PersonCommandRegister = Object.assign(new PersonCommandRegister(), this.form.value);
    this.service.post(command)
      .pipe(take(1))
      .subscribe((x: any) => {
        alert('Deu boa' + x);
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

  redirect(): void {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
