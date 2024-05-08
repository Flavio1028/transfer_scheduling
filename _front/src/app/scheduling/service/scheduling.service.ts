import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SchedulingService {

  private readonly API = '/transfer';

  constructor(private httpClient: HttpClient) { }

  save(record: any) {
    return this.httpClient.post<any>(`${this.API}/scheduling`, record).pipe(first());
  }

  getAllAccont() {
    return this.httpClient.get<any[]>(`${this.API}/account`).pipe(first());
  }

  getExtract(account: string) {
    return this.httpClient.get<any[]>(`${this.API}/scheduling/${account}`).pipe(first());
  }

}
