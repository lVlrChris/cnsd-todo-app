import { TestBed } from '@angular/core/testing';

import { ToDoTaskService } from './to-do-task.service';

describe('TaskServiceService', () => {
  let service: ToDoTaskService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ToDoTaskService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
