import { Injectable } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ToDoTask } from './to-do-task';
import { Observable, of } from 'rxjs';
import { Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ToDoTaskService {

  form: FormGroup = new FormGroup({
    id: new FormControl({value: null, disabled: true}, Validators.required),
    name: new FormControl('', Validators.required),
    description: new FormControl('')
  });

  private baseURL = 'http://localhost:8080/api/v1/';
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json'})};

  constructor(private http: HttpClient) { }

  getTasks(boardId: number, listId: number): Observable<ToDoTask[]> {
    const url = `${this.baseURL}boards/${boardId}/lists/${listId}/tasks`;
    return this.http.get<ToDoTask[]>(url);
  }

  createTask(boardId: number, listId: number, task: ToDoTask): Observable<ToDoTask> {
    const url = `${this.baseURL}boards/${boardId}/lists/${listId}/tasks`;
    return this.http.post<ToDoTask>(url, task, this.httpOptions);
  }

  updateTask(boardId: number, listId: number, task: ToDoTask): Observable<ToDoTask> {
    const url = `${this.baseURL}boards/${boardId}/lists/${listId}/tasks/${task.id}`;
    // console.log(task);
    return this.http.put<ToDoTask>(url, task, this.httpOptions);
  }

  initializeForm(): void {
    this.form.setValue({
      id: null,
      name: '',
      description: ''
    });
  }

  populateForm(task: ToDoTask): void {
    this.form.setValue(task);
  }

}
