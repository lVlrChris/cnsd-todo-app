import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ListService } from 'src/app/shared/list.service';

@Component({
  selector: 'app-task-list-detail',
  templateUrl: './task-list-detail.component.html',
  styleUrls: ['./task-list-detail.component.css']
})
export class TaskListDetailComponent implements OnInit {

  constructor(public listService: ListService,
              public dialogRef: MatDialogRef<TaskListDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public dialogData: any) { }

  ngOnInit(): void {
    this.dialogRef.afterClosed().subscribe(() => {
      this.onClose();
    });
  }

  onSubmit(): void {
    if (this.listService.form.valid) {
      const submittedList = this.listService.form.getRawValue();
      const boardId = this.dialogData.boardId;
      if (this.dialogData.createMode) { this.listService.createList(Number(boardId), submittedList).subscribe(); }
      if (this.dialogData.editMode) { this.listService.updateList(Number(boardId), submittedList).subscribe(); }

      this.listService.form.reset();
      this.listService.initializeForm();
      this.onClose();
    }
  }

  onClose(): void {
    this.listService.form.reset();
    this.listService.initializeForm();
    this.dialogRef.close();
  }

}
