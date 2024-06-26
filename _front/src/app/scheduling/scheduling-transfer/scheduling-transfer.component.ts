import { Component, LOCALE_ID, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { DateAdapter, provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

import { Error } from '../model/Error';
import { FormUtilsService } from '../service/FormUtilsService';
import { SchedulingService } from '../service/scheduling.service';


@Component({
  selector: 'app-scheduling-transfer',
  standalone: true,
  templateUrl: './scheduling-transfer.component.html',
  styleUrl: './scheduling-transfer.component.scss',
  providers: [provideNativeDateAdapter(), { provide: LOCALE_ID, useValue: 'pt-br' }],
  imports: [MatCardModule, MatToolbarModule, MatIconModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule,
      MatDatepickerModule, MatButtonModule, MatDividerModule, MatSelectModule],
})
export class SchedulingTransferComponent implements OnInit {

  form!: FormGroup;

  accounts: any[] = [];

  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date());
    let date = new Date();
    return day >= new Date(date.setDate(date.getDate() - 1));
  };

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private router: Router,
    private dateAdapter: DateAdapter<Date>,
    private service: SchedulingService,
    private _snackBar: MatSnackBar,
    private spinner: NgxSpinnerService,
    public formUtils: FormUtilsService) {}

    ngOnInit(): void {
      this.dateAdapter.setLocale('pt');
      this.loadForm();

    // Carega dados das contas
    this.service.getAllAccont().subscribe(
      (account: any) => {
        this.accounts = account?.content;
        this.spinner.hide();
      }, (__) => this.spinner.hide()
    );

  }

  onSubmit() {
    if(this.form.valid && this.validateTransferAccounts()) {
      this.spinner.show();
      // Formata a data
      this.formatDate(this.form.value?.schedulingDate);

      this.service.save(this.form.value).subscribe(
        (__) => {
          this._snackBar.open(`Agendamento realizado com sucesso.`, 'Ok', { duration: 5000 });
          this.loadForm();
          this.spinner.hide();
        },
        (value: any) => {
          this.spinner.hide();
          let error: Error = value.error;
          this._snackBar.open(`${error.details}`, 'Ok', { duration: 5000 });
        }
      );
    } else {
      this.formUtils.validateAllFormFields(this.form);
    }
  }


  loadForm() {
    this.form = this.formBuilder.group({
      originAccount: ['', Validators.required],
      destinationAccount: ['', Validators.required],
      schedulingDate: [new Date(), Validators.required],
      transferValue: ['', Validators.required]
    });
  }

  formatDate(schedulingDate: any) {
    if(schedulingDate) {
      this.form.value.schedulingDate = schedulingDate.toLocaleDateString("pt-br");
    }
  }

  validateTransferAccounts() : boolean {
    let originAccount = this.form.value?.originAccount;
    let destinationAccount = this.form.value?.destinationAccount;

    if(originAccount != null && destinationAccount != null && originAccount == destinationAccount) {
      this._snackBar.open(`A conta de origem e destino não podem ser a mesma.`, 'Ok', { duration: 5000 });
      return false;
    }
    return true;
  }

  onBack() {
    this.router.navigate(['home']);
  }

}
