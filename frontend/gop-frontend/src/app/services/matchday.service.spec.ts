import { TestBed } from '@angular/core/testing';

import { MatchdayService } from './matchday.service';

describe('MatchdayService', () => {
  let service: MatchdayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchdayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
