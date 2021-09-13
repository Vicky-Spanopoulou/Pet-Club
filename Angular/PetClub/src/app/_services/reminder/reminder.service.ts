import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IReminder } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/reminder/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class ReminderService {

  constructor(private http: HttpClient) { }

  createReminder(reminder: IReminder): Observable<IReminder>{
    return this.http.post<IReminder>(API_URL + 'create', reminder, httpOptions);
  }

  editReminder(reminder: IReminder): Observable<IReminder>{
    return this.http.post<IReminder>(API_URL + 'edit', reminder, httpOptions);
  }

  getAllReminders():  Observable<any>{
    return this.http.get(API_URL + 'getAll', { responseType: 'json' });
  }

  getAllRemindersByUserId(userId: number):  Observable<any>{
    return this.http.get(API_URL + 'getAllByUserId/' + userId, { responseType: 'json' });
  }

  getReminderById(id: number):  Observable<any>{
    return this.http.get(API_URL + 'get/' + id, { responseType: 'json' });
  }

  deleteReminderById(id: number): Observable<IReminder>{
    return this.http.get<IReminder>(API_URL + 'delete/' + id);
  }
}
