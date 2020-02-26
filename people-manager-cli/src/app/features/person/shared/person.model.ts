export class Person {
  public id: number;
  public name: string;
  public cpf: string;
  public email: string;
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
  constructor(id: number) {
    this.id = id;
  }

  public id: number;
  public name: string;
  public cpf: string;
  public email: string;
  public avatarId?: number;
  public birthDate: Date;
}

