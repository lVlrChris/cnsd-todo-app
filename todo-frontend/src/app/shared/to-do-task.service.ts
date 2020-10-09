import { Injectable } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { TASKS } from '../home/task-list/mock-tasks';
import { ToDoTask } from './to-do-task';
import { Observable, of } from 'rxjs';
import { Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ToDoTaskService {

  form: FormGroup = new FormGroup({
    id: new FormControl({value: null, disabled: true}, Validators.required),
    name: new FormControl('', Validators.required),
    description: new FormControl('')
  });

  constructor() { }

  getTasks(): Observable<ToDoTask[]> {
    return of(TASKS);
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
