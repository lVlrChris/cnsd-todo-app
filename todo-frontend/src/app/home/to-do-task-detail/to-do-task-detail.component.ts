import { Component, OnInit, Inject } from '@angular/core';
import { ToDoTaskService } from 'src/app/shared/to-do-task.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { debuglog } from 'util';

@Component({
  selector: 'app-to-do-task-detail',
  templateUrl: './to-do-task-detail.component.html',
  styleUrls: ['./to-do-task-detail.component.css']
})
export class ToDoTaskDetailComponent implements OnInit {

  constructor(public taskService: ToDoTaskService,
              public dialogRef: MatDialogRef<ToDoTaskDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public dialogData: any) { }

  ngOnInit(): void {
    this.dialogRef.afterClosed().subscribe(() => {
      this.onClose();
    });
  }

  onSubmit(): void {
    if (this.taskService.form.valid) {
      const submittedTask = this.taskService.form.getRawValue();
      const boardId = this.dialogData.boardId;
      const listId = this.dialogData.listId;
      if (this.dialogData.createMode) { this.taskService.createTask(Number(boardId), listId, submittedTask).subscribe(); }
      if (this.dialogData.editMode) { this.taskService.updateTask(Number(boardId), listId, submittedTask).subscribe(); }

      this.taskService.form.reset();
      this.taskService.initializeForm();
      this.onClose();
    }
  }

  onClose(): void {
    this.taskService.form.reset();
    this.taskService.initializeForm();
    this.dialogRef.close();
  }

}
