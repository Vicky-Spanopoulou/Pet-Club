import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ITransaction } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/transaction/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient) { }

  createTransaction(transaction: ITransaction): Observable<ITransaction>{
    return this.http.post<ITransaction>(API_URL + 'create', transaction, httpOptions);
  }

  updateTransaction(transaction: ITransaction): Observable<ITransaction>{
    return this.http.post<ITransaction>(API_URL + 'updatePayment', transaction, httpOptions);
  }

  getAllTransactions():  Observable<any>{
    return this.http.get(API_URL + 'getAll', { responseType: 'json' });
  }

  getTransactionById(id: number):  Observable<any>{
    return this.http.get(API_URL + 'get/' + id, { responseType: 'json' });
  }

}
