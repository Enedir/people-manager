import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private baseUrl: string;

 

  constructor(private _http: HttpClient) {
    this.baseUrl = environment.url;
  }

  uploadImage(files: Set<File>) {

    const formData = new FormData();
    files.forEach(file => formData.append('file', file, file.name));

    return this._http.post(`${this.baseUrl}/uploads/images`, formData, {
      observe: 'events',
      reportProgress: true
    });
  }
}
