import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { Validators } from '@angular/forms';
import { List } from './list';

@Injectable({
  providedIn: 'root'
})
export class ListService {

  form: FormGroup	= new FormGroup({
    id: new FormControl({value: null, disabled: true}, Validators.required),
    name: new FormControl('', Validators.required)
  });

  private baseURL = 'http://localhost:8080/api/v1/';
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json'})};

  constructor(private http: HttpClient) { }

  getLists(boardId: number): Observable<List[]> {
    const url = `${this.baseURL}boards/${boardId}/lists`;
    return this.http.get<List[]>(url);
  }

  getList(boardId: number, id: number): Observable<List> {
    const url = `${this.baseURL}boards/${boardId}/lists/${id}`;
    return this.http.get<List>(url);
  }

  createList(boardId: number, list: List): Observable<List> {
    const url = `${this.baseURL}boards/${boardId}/lists`;
    return this.http.post<List>(url, list, this.httpOptions);
  }

  updateList(boardId: number, list: List): Observable<List> {
    const url = `${this.baseURL}boards/${boardId}/lists/${list.id}`;
    return this.http.put<List>(url, list, this.httpOptions);
  }

  initializeForm(): void {
    this.form.setValue({
      id: null,
      name: ''
    });
  }

  populateForm(list: List): void {
    this.form.setValue({id: list.id, name: list.name});
  }
}
