import { TestBed } from '@angular/core/testing';

import { CompetitionsService } from './competitions.service';

describe('CompetitonsService', () => {
  let service: CompetitionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompetitionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
