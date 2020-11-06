import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { List } from 'src/app/shared/list';
import { ListService } from 'src/app/shared/list.service';
import { ToDoTaskService } from 'src/app/shared/to-do-task.service';
import { ToDoTask } from '../../shared/to-do-task';
import { TaskListDetailComponent } from '../task-list-detail/task-list-detail.component';
import { ToDoTaskDetailComponent } from '../to-do-task-detail/to-do-task-detail.component';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  @Input() list: List;
  tasks: ToDoTask[];

  constructor(
    private route: ActivatedRoute,
    private taskService: ToDoTaskService,
    private listService: ListService,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getTasks();
  }

  getTasks(): void {
    const boardId = this.route.snapshot.paramMap.get('boardId');
    this.taskService.getTasks(Number(boardId), this.list.id).subscribe(tasks => this.tasks = tasks);
  }

  onCreateTask(): void {
    const boardId = this.route.snapshot.paramMap.get('boardId');
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = { boardId, listId: this.list.id, createMode: true};
    this.dialog.open(ToDoTaskDetailComponent, dialogConfig);
  }

  onEditTask(task: ToDoTask): void {
    const boardId = this.route.snapshot.paramMap.get('boardId');
    this.taskService.populateForm(task);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = { boardId, listId: this.list.id, editMode: true};
    this.dialog.open(ToDoTaskDetailComponent, dialogConfig);
  }

  onEditList(list: List): void {
    const boardId = this.route.snapshot.paramMap.get('boardId');
    this.listService.populateForm(list);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = { boardId, editMode: true };
    this.dialog.open(TaskListDetailComponent, dialogConfig);
  }
}
