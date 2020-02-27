import { PersonService } from './person.service';
import { AbstractControl } from '@angular/forms';
import { map } from 'rxjs/internal/operators/map';


export class ValidateRepeatedCpf {
  static checkCpfIsRepeated(personService: PersonService) {
    return (control: AbstractControl) => {
      return personService.IsCpfRepeated(control.value).pipe(map(res => {
        return res ?  { cpfRepeated : true } : null;
      }));
    };
  }
}