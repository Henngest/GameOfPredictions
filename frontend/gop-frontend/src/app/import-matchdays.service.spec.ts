import { TestBed } from '@angular/core/testing';

import { ImportMatchdaysService } from './import-matchdays.service';

describe('ImportMatchdaysService', () => {
  let service: ImportMatchdaysService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImportMatchdaysService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
