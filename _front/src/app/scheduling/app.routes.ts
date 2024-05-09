import { Routes } from '@angular/router';

import { ExtractComponent } from './extract/extract.component';
import { HomeComponent } from './home/home.component';
import { SchedulingTransferComponent } from './scheduling-transfer/scheduling-transfer.component';


export const APP_ROUTES: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'schedule', component: SchedulingTransferComponent },
  { path: 'extract', component: ExtractComponent }
];
