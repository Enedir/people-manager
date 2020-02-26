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


@Component({
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.scss']
})
export class PersonListComponent implements OnInit {

  constructor(private service: PersonService, 
              private router: Router, 
              private route: ActivatedRoute,
              public dialog: MatDialog) { }

  dataGrid: MatTableDataSource<Person>;
  displayedColumns: string[] = ['name', 'cpf', 'email', 'birthDate','actions'];
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  public searchKey: string;

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
          }, (err: HttpErrorResponse) => {
            if (err.error instanceof Error) {
              // A client-side or network error occurred.
              // this.spinner.hide();
              alert('Aconteceu um erro:' + err.error.message);
            } else {
              // Backend returns unsuccessful response codes such as 404, 500 etc.
              alert('Aconteceu um erro: status ->  ' + err.status + 'mensagem de erro -> ' + err.error);
              // this.spinner.hide();
              // Log errors if any
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
