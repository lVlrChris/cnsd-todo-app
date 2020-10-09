import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Board } from 'src/app/shared/board';
import { BoardService } from 'src/app/shared/board.service';
import { List } from 'src/app/shared/list';
import { ListService } from 'src/app/shared/list.service';

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
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getBoard();
  }

  getBoard(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (!id) { this.router.navigate(['/board/0']); }
    this.boardService.getBoard(Number(id))
      .subscribe((board) => {
        this.board = board;
        this.getListsOfBoard(board.id);
      });
  }

  getListsOfBoard(id: number): void {
    this.listService.getListsOfBoard(id).subscribe(lists => this.lists = lists);
  }

}
