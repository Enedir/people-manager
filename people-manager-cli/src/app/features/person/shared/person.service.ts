import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap, catchError } from 'rxjs/operators'
import { environment } from './../../../../environments/environment.prod';

import { Person } from './person.model';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private baseUrl: string;

  constructor(private _http: HttpClient) {
    this.baseUrl = environment.url;
}

  public get(id: number): Observable<Person> {

    return this._http.get<Person>(`${this.baseUrl}/persons/${id}`);
}

}


@Injectable({
  providedIn: 'root'
})
export class PersonResolveService implements Resolve<Person> {

  constructor(private service: PersonService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Person> | Observable<never> {

      const solicitationId = Number(route.params['personId']);

      return this.service.get(solicitationId).pipe(catchError(error => {
          return EMPTY;
      }), mergeMap(solicitation => {
          if (solicitation) {
              return of(solicitation);
          } else {
              return EMPTY;
          }
      })
      )
  }
}

