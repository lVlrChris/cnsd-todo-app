import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToDoTaskDetailComponent } from './to-do-task-detail.component';

describe('ToDoTaskDetailComponent', () => {
  let component: ToDoTaskDetailComponent;
  let fixture: ComponentFixture<ToDoTaskDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToDoTaskDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ToDoTaskDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
