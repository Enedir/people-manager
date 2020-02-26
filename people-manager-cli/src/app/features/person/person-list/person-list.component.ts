import { Person } from './../shared/person.model';
import { Component, OnInit, ViewChild } from '@angular/core';

import { PersonService } from '../shared/person.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.scss']
})
export class PersonListComponent implements OnInit {

  constructor(private service: PersonService) { }

  dataGrid: MatTableDataSource<Person>;
  displayedColumns: string[] = ['name', 'cpf', 'email', 'birthDate','actions'];
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  public searchKey: string;

  ngOnInit(): void {
    this.service.show().subscribe(
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

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  applyFilter() {
    this.dataGrid.filter = this.searchKey.trim().toLowerCase();
  }

}
