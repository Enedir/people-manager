import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { FileService } from './../../../shared/file/file.service';
import { filterResponse, uploadProgress } from './../../../shared/rxjs-operators';

import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'sajadv-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.scss']
})
export class PersonFormComponent {

  private progress = 0;

  @Input() public previewUrl: string | ArrayBuffer = '';

  @Input() public formModel: FormGroup;

  @Output() public updaloadResponse: EventEmitter<number> = new EventEmitter();

  constructor(private fileService: FileService, public snackBar: MatSnackBar) { }

  loadImage(event: any) {

    const selectedFiles = <FileList>event.srcElement.files;

    let fileNames = [];
    let files = new Set<File>();
    for (let i = 0; i < selectedFiles.length; i++) {
      fileNames.push(selectedFiles[i].name);
      files.add(selectedFiles[i]);
    }

    if (files && files.size > 0) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);

      reader.onload = () => {
        this.previewUrl = reader.result;
        this.fileService.uploadImage(files)
          .pipe(uploadProgress(progress => {
            this.progress = progress;
          }),
            filterResponse()
          ).subscribe(response => {
            this.updaloadResponse.emit(<number>response);
          }, (err: HttpErrorResponse) => {
            if (err.error instanceof Error) {
              this.snackBar.open('Aconteceu um erro de conexão com o servidor teste mais tarde.', 'Fechar', {
                duration: 2000,
                panelClass: ['blue-snackbar']
              });
            } else {
              this.snackBar.open('Aconteceu um erro: status ->  ' + err.status + 'mensagem de erro -> ' + err.error, 'Fechar', {
                duration: 2000,
                panelClass: ['red-snackbar']
              });
            }
          });
      }
    }
    else {
      alert('Não foi possível carregar a imagem.');
    }
  }
}
