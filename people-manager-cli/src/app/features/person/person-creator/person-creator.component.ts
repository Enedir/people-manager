import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CpfCnpjValidators } from '../shared/cpf-validator';

@Component({
  templateUrl: './person-creator.component.html',
  styleUrls: ['./person-creator.component.scss']
})
export class PersonCreatorComponent implements OnInit {

  public form: FormGroup = this.fb.group({
    $key: null,
    name: ['', Validators.required, Validators.maxLength(150)],
    cpf: ['', [Validators.required, CpfCnpjValidators.checkCpf]],
    email: ['', [Validators.email, Validators.maxLength(400)]],
    birthDate: ['', [Validators.required]],
  });

  constructor(private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onCreate(): void{
    
  }

  redirect(): void {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

}
