import { Component, OnInit, Inject } from '@angular/core';
import { ToDoTaskService } from 'src/app/shared/to-do-task.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

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
      // TODO: Create task
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
