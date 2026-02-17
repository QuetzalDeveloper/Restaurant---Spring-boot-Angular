import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { API_ENDPOINTS } from '../config/API_ENDPOINTS';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private http = inject(HttpClient);

  public getCategories() : Observable<any[]> {
    return this.http.get<any[]>(environment.apiUrlMenu + API_ENDPOINTS.MENU.GET_CATEGORIES);
  }

  public upsertCategory(data: any) : Observable<any> {
    return this.http.post<any>(environment.apiUrlMenu + API_ENDPOINTS.MENU.UPSERT_CATEGOY, data);
  }
}
