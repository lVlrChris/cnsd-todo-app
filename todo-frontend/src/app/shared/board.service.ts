import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Board } from './board';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  boards: Board[] = [
    { id: 0, name: 'Board 1' },
    { id: 1, name: 'Board 2' },
    { id: 2, name: 'Board 3' }
  ];

  constructor() { }

  getBoards(): Observable<Board[]> {
    return of(this.boards);
  }

  getBoard(id: number): Observable<Board> {
    return of(this.boards.find(b => b.id === id));
  }
}
