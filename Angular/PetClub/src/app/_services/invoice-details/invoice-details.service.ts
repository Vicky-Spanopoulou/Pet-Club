import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IInvoiceDetails } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/invoiceDetails/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class InvoiceDetailsService {

  constructor(private http: HttpClient) {}

  createInvoiceDetails(invoice: IInvoiceDetails): Observable<IInvoiceDetails>{
    return this.http.post<IInvoiceDetails>(API_URL + 'create', invoice, httpOptions);
  }

  editInvoiceDetails(invoice: IInvoiceDetails): Observable<IInvoiceDetails>{
    return this.http.post<IInvoiceDetails>(API_URL + 'edit', invoice, httpOptions);
  }

  getAllInvoiceDetails():  Observable<any>{
    return this.http.get(API_URL + 'getAll', { responseType: 'json' });
  }
  getInvoiceByUserId(id:number): Observable<any>{
    return this.http.get(API_URL + 'getUserInvoice/' + id);
  }

  getInvoiceDetailsById(id: number):  Observable<any>{
    return this.http.get(API_URL + 'get/' + id, { responseType: 'json' });
  }

  deleteInvoiceDetailsById(id: number): Observable<IInvoiceDetails>{
    return this.http.get<IInvoiceDetails>(API_URL + 'delete/' + id);
  }

}
