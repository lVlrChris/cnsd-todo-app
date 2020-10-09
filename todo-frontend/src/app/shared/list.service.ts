import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { List } from './list';

@Injectable({
  providedIn: 'root'
})
export class ListService {

  lists: List[] = [
    { id: 0, name: 'List 1' },
    { id: 1, name: 'List 2' },
    { id: 2, name: 'List 3' },
    { id: 3, name: 'List 4' },
    { id: 4, name: 'List 5' }
  ];

  constructor() { }

  getLists(): Observable<List[]> {
    return of(this.lists);
  }

  getList(id: number): Observable<List> {
    return of(this.lists.find(l => l.id === id));
  }

  getListsOfBoard(boardId: number): Observable<List[]> {
    return of(this.lists);
  }
}
