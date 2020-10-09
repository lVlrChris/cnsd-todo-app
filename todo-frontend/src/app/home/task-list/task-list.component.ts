import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogConfig } from '@angular/material/dialog';
import { ToDoTaskService } from 'src/app/shared/to-do-task.service';
import { ToDoTask } from './to-do-task';
import { ToDoTaskDetailComponent } from '../to-do-task-detail/to-do-task-detail.component';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks: ToDoTask[];
  selectedTask: ToDoTask;

  constructor(private taskService: ToDoTaskService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getTasks();
  }

  getTasks(): void {
    this.taskService.getTasks().subscribe(tasks => this.tasks = tasks);
  }

  onCreateTask(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {createMode: true};
    this.dialog.open(ToDoTaskDetailComponent, dialogConfig);
  }

  onEditTask(task: ToDoTask): void {
    this.taskService.populateForm(task);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {editMode: true};
    this.dialog.open(ToDoTaskDetailComponent, dialogConfig);
  }
}
