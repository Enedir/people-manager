import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { take } from 'rxjs/internal/operators/take';
import { Person } from './../shared/person.model';

import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';

import { PersonContextRemoveDialog } from '../shared/person-context-remove-dialog/person-context-remove.dialog';

import { PersonService } from '../shared/person.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.scss']
})
export class PersonListComponent implements OnInit {

  public searchKey: string;

  dataGrid: MatTableDataSource<Person>;
  displayedColumns: string[] = ['name', 'cpf', 'email', 'birthDate','actions'];
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: PersonService, 
              private router: Router, 
              private route: ActivatedRoute,
              public dialog: MatDialog,
              public snackBar: MatSnackBar) { }

 
  ngOnInit(): void {
    this.refresh();
  }

  onEdit(person : Person){
    this.router.navigate(['./edit', `${person.id}`], { relativeTo: this.route });
  }

  openRemoveDialog(person : Person) { 
    const dialogRef = this.dialog.open(PersonContextRemoveDialog);

    dialogRef.afterClosed().subscribe(result => {
        if(result){
          this.service.delete(person.id)
          .pipe(take(1))
          .subscribe((x: any) => {
            this.refresh();
            this.snackBar.open('Ação realizado com sucesso.', 'Fechar', {
              duration: 2000,
              panelClass: ['green-snackbar']
            });
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
    });
  }

  onSearchClear(): void {
    this.searchKey = "";
    this.applyFilter();
  }

  applyFilter(): void {
    this.dataGrid.filter = this.searchKey.trim().toLowerCase();
  }

  private refresh(): void {

    this.service.show().pipe(take(1)).subscribe(
      persons => {
        this.dataGrid = new MatTableDataSource(persons);
        this.dataGrid.sort = this.sort;
        this.dataGrid.paginator = this.paginator;
        this.dataGrid.filterPredicate = (data, filter) => {
          return this.displayedColumns.some(ele => {
            return ele != 'actions' && data[ele].toLowerCase().indexOf(filter) != -1;
          });
        };
      });
  }

}
