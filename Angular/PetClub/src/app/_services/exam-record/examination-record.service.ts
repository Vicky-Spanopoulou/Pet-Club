import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IExamRecord } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/exams/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export default class ExaminationRecordService {

  constructor(private http: HttpClient) { }

  createExamRecord(exam: IExamRecord): Observable<IExamRecord>{
    return this.http.post<IExamRecord>(API_URL + 'create', exam, httpOptions);
  }

  editExamRecord(exam: IExamRecord): Observable<IExamRecord>{
    return this.http.post<IExamRecord>(API_URL + 'edit', exam, httpOptions);
  }

  getAllExamRecords():  Observable<any>{
    return this.http.get(API_URL + 'getAll', { responseType: 'json' });
  }

  getExamRecordById(id: number):  Observable<any>{
    return this.http.get(API_URL + 'get/' + id, { responseType: 'json' });
  }

  getExamRecordByPet(petId: number):  Observable<any>{
    return this.http.get(API_URL + 'getByPet/' + petId, { responseType: 'json' });
  }

  deleteExamRecordById(id: number): Observable<IExamRecord>{
    return this.http.get<IExamRecord>(API_URL + 'delete/' + id);
  }

}
