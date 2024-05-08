import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
  imports: [MatCardModule, MatToolbarModule, MatIconModule, MatDatepickerModule, MatButtonModule, MatDividerModule]
})
export class HomeComponent {

  constructor(private router: Router) { }

  onClickScheduling() {
    this.router.navigate(['home/transfer']);
  }

  onClickExtract() {
    this.router.navigate(['home/extract']);
  }

}
