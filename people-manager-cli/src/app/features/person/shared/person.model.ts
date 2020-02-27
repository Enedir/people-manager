export class Person {
  public id: number;
  public name: string;
  public cpf: string;
  public email: string;
  public avatarId?: number;
  public url?: string;
  public birthDate: Date;
}

export class PersonCommandRegister {
  public name: string;
  public cpf: string;
  public email: string;
  public avatarId?: number;
  public birthDate: Date;
}

export class PersonCommandUpdate {
  public id: number;
  public name: string;
  public cpf: string;
  public email: string;
  public avatarId?: number;
  public birthDate: Date;
}

