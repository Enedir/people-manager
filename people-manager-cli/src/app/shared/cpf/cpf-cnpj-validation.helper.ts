/* tslint:disable */
export class CpfCnpjValidationHelper {

  private isNullOrWhitespace(source: string): boolean {

      if (typeof source === 'undefined' || source === null) return true;

      return source.replace(/\s/g, '').length < 1;
  }
  public isCPF(cpf: string): boolean {

      if (this.isNullOrWhitespace(cpf)) {
          return false;
      }

      let clearCPF: string;
      clearCPF = cpf.trim();
      clearCPF = clearCPF.replace('-', '');
      clearCPF = clearCPF.replace('.', '');
      clearCPF = clearCPF.replace('.', '');

      if (clearCPF.length !== 11) {
          return false;
      }

      let cpfArray: number[];
      let totalDigitoI: number = 0;
      let totalDigitoII: number = 0;
      let modI: number;
      let modII: number;

      if (clearCPF === '00000000000' ||
          clearCPF === '11111111111' ||
          clearCPF === '22222222222' ||
          clearCPF === '33333333333' ||
          clearCPF === '44444444444' ||
          clearCPF === '55555555555' ||
          clearCPF === '66666666666' ||
          clearCPF === '77777777777' ||
          clearCPF === '88888888888' ||
          clearCPF === '99999999999') {
          return false;
      }

      const i: string = clearCPF[0];

      if (isNaN(Number(clearCPF))) {
          return false;
      }

      cpfArray = new Array<number>(11);
      for (let i = 0; i < clearCPF.length; i++) {
          cpfArray[i] = Number(clearCPF[i].toString());
      }

      for (let posicao = 0; posicao < cpfArray.length - 2; posicao++) {
          totalDigitoI += cpfArray[posicao] * (10 - posicao);
          totalDigitoII += cpfArray[posicao] * (11 - posicao);
      }

      modI = totalDigitoI % 11;
      if (modI < 2) { modI = 0; }
      else { modI = 11 - modI; }

      if (cpfArray[9] != modI) {
          return false;
      }

      totalDigitoII += modI * 2;

      modII = totalDigitoII % 11;
      if (modII < 2) { modII = 0; }
      else { modII = 11 - modII; }
      if (cpfArray[10] != modII) {
          return false;
      }

      return true;
  }

  public isCNPJ(cnpj: string): boolean {
      if (this.isNullOrWhitespace(cnpj))
          return false;

      cnpj = cnpj.trim();
      cnpj = cnpj.replace('.', '').replace('-', '').replace('/', '');

      if (cnpj.length != 14)
          return false;

      let multiplicador1: number[] = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2];
      let multiplicador2: number[] = [6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2];

      let soma: number;
      let resto: number;
      let digit: string;
      let tempCnpj: string;

      if (cnpj === '00000000000' ||
          cnpj === '11111111111' ||
          cnpj === '22222222222' ||
          cnpj === '33333333333' ||
          cnpj === '44444444444' ||
          cnpj === '55555555555' ||
          cnpj === '66666666666' ||
          cnpj === '77777777777' ||
          cnpj === '88888888888' ||
          cnpj === '99999999999') {
          return false;
      }

      tempCnpj = cnpj.substring(0, 12);
      soma = 0;

      for (let i = 0; i < 12; i++)
      soma += Number(tempCnpj[i].toString()) * multiplicador1[i];

      resto = (soma % 11);

      if (resto < 2)
          resto = 0;
      else
          resto = 11 - resto;

      digit = resto.toString();
      tempCnpj = tempCnpj + digit;
      soma = 0;

      for (let i = 0; i < 13; i++)
      soma += Number(tempCnpj[i].toString()) * multiplicador2[i];

      resto = (soma % 11);

      if (resto < 2)
          resto = 0;
      else
          resto = 11 - resto;

      digit = digit + resto.toString();

      return cnpj.endsWith(digit);
  }

}
