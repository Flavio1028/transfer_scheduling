import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NgxSpinnerService } from 'ngx-spinner';

import { Extract } from '../model/Extract';
import { SchedulingService } from '../service/scheduling.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-extract',
  standalone: true,
  imports: [MatCardModule, MatToolbarModule, MatIconModule,  MatInputModule, MatButtonModule, MatDividerModule, FormsModule, MatFormFieldModule, MatSelectModule, MatTableModule, CommonModule],
  templateUrl: './extract.component.html',
  styleUrl: './extract.component.scss'
})
export class ExtractComponent implements OnInit {

  accounts: any[] = [];

  selectedAccount: string = "";

  displayedColumns: string[] = ['customerName', 'transferValue', 'tax', 'dataTransfer', 'schedulingDate', 'beneficiaryName', 'beneficiaryAccount'];
  extractList : Extract[] = [];

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
    this.servie.getExtract(this.selectedAccount).subscribe(
      (extract: any) => {
        this.extractList = extract?.content;
        console.log(this.extractList);
        this.spinner.hide();
      }, (__) => this.spinner.hide()
    );
  }

  showTable() :boolean {
    return this.extractList.length > 0 && this.selectedAccount != '';
  }

  showMessageAlert() :boolean {
    return this.extractList.length == 0 && this.selectedAccount != '';
  }

  onBack() {
    this.router.navigate(['home']);
  }



}
