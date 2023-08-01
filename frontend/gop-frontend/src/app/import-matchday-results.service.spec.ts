import { TestBed } from '@angular/core/testing';

import { ImportMatchdayResultsService } from './import-matchday-results.service';

describe('ImportMatchdayResultsService', () => {
  let service: ImportMatchdayResultsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImportMatchdayResultsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
