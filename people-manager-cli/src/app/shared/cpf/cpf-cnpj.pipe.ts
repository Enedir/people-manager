import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'sajadvCpf' })
/* tslint:disable */
export class CpfCnpjPipe implements PipeTransform {

  public transform(value: string): string {
    if (value) {
      value = value.toString();

      return this.formattedCPF(value);
    }

    return value;
  }

  private formattedCPF(value: string): string {
    return value.substring(0, 3).concat('.')
      .concat(value.substring(3, 6))
      .concat('.')
      .concat(value.substring(6, 9))
      .concat('-')
      .concat(value.substring(9, 11));
  }
}
