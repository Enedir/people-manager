import { AbstractControl, ValidationErrors } from '@angular/forms';
import { CpfCnpjValidationHelper } from './cpf-cnpj-validation.helper';

export class CpfCnpjValidators {
    public static checkCpf(cpfControl: AbstractControl): ValidationErrors | null {

        if (!(cpfControl)) return null;

        const cpf: string = cpfControl.value;

        const validation: CpfCnpjValidationHelper = new CpfCnpjValidationHelper();
        const isValid: boolean = validation.isCPF(cpf);

        return isValid ? null : { invalidCpf: true };
    }s
}
