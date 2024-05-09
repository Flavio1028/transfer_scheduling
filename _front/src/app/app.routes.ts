import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  {
    path: '',
    loadChildren: () => import('./scheduling/app.routes').then(m => m.APP_ROUTES)
  },
  { path: '**', redirectTo: 'home' }
];
