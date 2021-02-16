import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { Observable } from 'rxjs';

import { environment } from './../../../../environments/environment';

import { Person, PersonCommandRegister, PersonCommandUpdate } from './person.model';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private baseUrl: string;

  constructor(private _http: HttpClient) {
    this.baseUrl = environment.url;
  }

  public delete(id: number): Observable<boolean> {
    return this._http.delete<any>(`${this.baseUrl}/persons/${id}`);
  }

  public get(id: number): Observable<Person> {
    return this._http.get<Person>(`${this.baseUrl}/persons/${id}`);
  }

  public post(commad: PersonCommandRegister): Observable<any> {
    return this._http.post(`${this.baseUrl}/persons/`, commad);
  }

  public put(commad: PersonCommandUpdate): Observable<any> {
    return this._http.put(`${this.baseUrl}/persons/`, commad);
  }

  public show(): Observable<Person[]> {
    return this._http.get<Person[]>(`${this.baseUrl}/persons`);
  }

  public IsCpfRepeated(cpf: string) : Observable<any> {
    return this._http.get(`${this.baseUrl}/persons/checkCpfIsRepeated/${cpf}`);
  }

}


@Injectable()
export class PersonResolveService implements Resolve<Person> {

  constructor(private service: PersonService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Person> | Promise<Person> | any {
    return this.service.get(Number(route.paramMap.get('personId')));
  }


}

