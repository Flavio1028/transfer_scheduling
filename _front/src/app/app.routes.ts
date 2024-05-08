import { APP_ROUTES } from './scheduling/app.routes';
import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  {
    path: 'home',
    loadChildren: () => import('./scheduling/app.routes').then(m => m.APP_ROUTES)
  }
];
