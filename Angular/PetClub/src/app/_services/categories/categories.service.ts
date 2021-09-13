import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IBusinessCategory, ICity, IPetCategory } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/categories/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private http: HttpClient) { }

  // city
  createCity(city: ICity): Observable<ICity>{
    return this.http.post<ICity>(API_URL + 'city/create', city, httpOptions);
  }

  editCity(city: ICity): Observable<ICity>{
    return this.http.post<ICity>(API_URL + 'city/edit', city, httpOptions);
  }

  getCityById(id: number):  Observable<ICity>{
    return this.http.get<ICity>(API_URL + 'city/getCityById/' + id, { responseType: 'json' });
  }

  getCityByName(name: string):  Observable<ICity>{
    return this.http.get<ICity>(API_URL + 'city/getCityByName/' + name, { responseType: 'json' });
  }

  getAllCities():  Observable<any>{
    return this.http.get(API_URL + 'city/getAll', { responseType: 'json' });
  }

  deleteCityById(id: number): Observable<ICity>{
    return this.http.get<ICity>(API_URL + 'city/delete/' + id);
  }

  //pet category
  createPetCategory(petCategory: IPetCategory): Observable<IPetCategory>{
    return this.http.post<IPetCategory>(API_URL + 'pet_category/create', petCategory, httpOptions);
  }

  editPetCategory(petCategory: IPetCategory): Observable<IPetCategory>{
    return this.http.post<IPetCategory>(API_URL + 'pet_category/edit', petCategory, httpOptions);
  }
  
  getPetCategoryById(id: number):  Observable<IPetCategory>{
    return this.http.get<IPetCategory>(API_URL + 'pet_category/getPetCat/' + id, { responseType: 'json' });
  }

  getAllPetCategories():  Observable<any>{
    return this.http.get(API_URL + 'pet_category/getAll', { responseType: 'json' });
  }

  deletePetCategoryById(id: number): Observable<IPetCategory>{
    return this.http.get<IPetCategory>(API_URL + 'pet_category/delete/' + id);
  }

  //pet sex
  getAllPetSexes():  Observable<any>{
    return this.http.get(API_URL + 'pet_sex/getAll', { responseType: 'json' });
  }

  //exam category
  getAllExamCategories():  Observable<any>{
    return this.http.get(API_URL + 'exam_cat/getAll', { responseType: 'json' });
  }

  //roles
  getAllRoles(): Observable<any>{
    return this.http.get(API_URL + 'roles/getAll', { responseType: 'json' });
  }

  //ad categories
  getAllAdCategories(): Observable<any>{
    return this.http.get(API_URL + 'adCategories/getAll', { responseType: 'json' });
  }

  //transaction categories
  getAllTransactionCategories(): Observable<any>{
    return this.http.get(API_URL + 'transactionCat/getAll', { responseType: 'json'});
  }

  //page status
  getAllPageStatuses(): Observable<any>{
    return this.http.get(API_URL + 'PageStatus/getAll', { responseType: 'json'});
  }

  //business categories
  createBusinessCategory(bCategory: IBusinessCategory): Observable<IBusinessCategory>{
    return this.http.post<IBusinessCategory>(API_URL + 'businessCat/create', bCategory, httpOptions);
  }

  editBusinessCategory(bCategory: IBusinessCategory): Observable<IBusinessCategory>{
    return this.http.post<IBusinessCategory>(API_URL + 'businessCat/edit', bCategory, httpOptions);
  }
  
  getBusinessCategoryById(id: number):  Observable<IBusinessCategory>{
    return this.http.get<IBusinessCategory>(API_URL + 'businessCat/getBusinessCatById/' + id, { responseType: 'json' });
  }

  getBusinessCategoryByName(name: string):  Observable<IBusinessCategory>{
    return this.http.get<IBusinessCategory>(API_URL + 'businessCat/getBusinessCatByName/' + name, { responseType: 'json' });
  }

  getAllBusinessCategories():  Observable<any>{
    return this.http.get(API_URL + 'businessCat/getAll', { responseType: 'json' });
  }

  deleteBusinessCategoryById(id: number): Observable<IBusinessCategory>{
    return this.http.get<IBusinessCategory>(API_URL + 'businessCat/delete/' + id);
  }





}
