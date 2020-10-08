import { BoundAttribute } from '@angular/compiler/src/render3/r3_ast';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  boards: string[] = [
    'Board 1',
    'Board 2',
    'Board 3'
  ];
  currentBoard = this.boards[0];

  constructor() { }

  ngOnInit(): void {
  }
}
