import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Board } from './board';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  private baseURL = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getBoards(): Observable<Board[]> {
    const url = `${this.baseURL}boards`;
    return this.http.get<Board[]>(url);
  }

  getBoard(id: number): Observable<Board> {
    const url = `${this.baseURL}boards/${id}`;
    return this.http.get<Board>(url);
  }
}
