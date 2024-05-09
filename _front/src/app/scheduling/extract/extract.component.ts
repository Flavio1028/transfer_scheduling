import { CommonModule } from '@angular/common';
import { Component, Injectable, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subject } from 'rxjs';

import { Extract } from '../model/Extract';
import { SchedulingService } from '../service/scheduling.service';

@Injectable()
export class MyCustomPaginatorIntl implements MatPaginatorIntl {
  changes = new Subject<void>();

  firstPageLabel = `Primeira página`;
  itemsPerPageLabel = `Itens por página:`;
  lastPageLabel = `Ultima página`;

  nextPageLabel = 'Próxima';
  previousPageLabel = 'Anterior';

  getRangeLabel(page: number, pageSize: number, length: number): string {
    if (length === 0) {
      return `Página 1 de 1`;
    }
    const amountPages = Math.ceil(length / pageSize);
    return `Página ${page + 1} de ${amountPages}`;
  }
}

@Component({
  selector: 'app-extract',
  standalone: true,
  imports: [MatCardModule, MatToolbarModule, MatIconModule,  MatInputModule, MatButtonModule, MatDividerModule, FormsModule, MatFormFieldModule,
    MatSelectModule, MatTableModule, CommonModule, MatPaginatorModule],
  templateUrl: './extract.component.html',
  styleUrl: './extract.component.scss',
  providers: [{provide: MatPaginatorIntl, useClass: MyCustomPaginatorIntl}],
})
export class ExtractComponent implements OnInit {

  accounts: any[] = [];

  selectedAccount: string = "";

  displayedColumns: string[] = ['customerName', 'transferValue', 'tax', 'dataTransfer', 'schedulingDate', 'beneficiaryName', 'beneficiaryAccount'];
  extractList : Extract[] = [];

  page: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;

  constructor(
    private servie: SchedulingService,
    private spinner: NgxSpinnerService,
    private router: Router
  )  {}

    ngOnInit(): void {
      this.spinner.show();

      this.servie.getAllAccont().subscribe(
        (account: any) => {
          this.accounts = account?.content;
          this.spinner.hide();
        }, (__) => this.spinner.hide()
      );

  }

  loadExtract() {
    this.spinner.show();

    this.servie.getExtract(this.selectedAccount, this.page, this.pageSize).subscribe(
      (extract: any) => {
        this.extractList = extract?.content;
        // Total elements pagination
        this.totalElements = extract?.totalElements
        this.spinner.hide();
      }, (__) => this.spinner.hide()
    );
  }

  showTable() :boolean {
    return this.extractList.length > 0 && this.selectedAccount != '';
  }

  getExtracts(event: any) {
    this.page = event?.pageIndex;
    this.loadExtract();
  }

  showMessageAlert() :boolean {
    return this.extractList.length == 0 && this.selectedAccount != '';
  }

  onBack() {
    this.router.navigate(['home']);
  }



}
