import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Board } from 'src/app/shared/board';
import { BoardService } from 'src/app/shared/board.service';
import { List } from 'src/app/shared/list';
import { ListService } from 'src/app/shared/list.service';
import { TaskListDetailComponent } from '../task-list-detail/task-list-detail.component';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  board: Board;
  lists: List[];

  constructor(
    private route: ActivatedRoute,
    private boardService: BoardService,
    private listService: ListService,
    private location: Location,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    const boardId = this.route.snapshot.paramMap.get('boardId');
    if (!this.route.snapshot.paramMap.get('boardId')) {
      this.getFirstBoard();
    } else {
      this.getBoard(Number(boardId));
    }
  }

  getFirstBoard(): void {
    this.boardService.getBoards()
      .subscribe((boards) => {
        if (boards.length > 0) {
          this.router.navigate([`/board/${boards[0].id}`]);
        }
      });
  }

  getBoard(id: number): void {
    this.boardService.getBoard(Number(id))
      .subscribe((board) => {
        this.board = board;
        this.getListsOfBoard(board.id);
      });
  }

  getListsOfBoard(boardId: number): void {
    this.listService.getLists(boardId).subscribe(lists => this.lists = lists);
  }

  onCreateList(): void {
    const boardId = this.route.snapshot.paramMap.get('boardId');
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = { boardId, createMode: true };
    this.dialog.open(TaskListDetailComponent, dialogConfig);
  }
}
