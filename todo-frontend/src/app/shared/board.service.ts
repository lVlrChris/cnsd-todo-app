import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Board } from './board';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  private baseURL = 'http://localhost:8080/api/v1/';

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
