import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportMatchdayResultsComponent } from './import-matchday-results.component';

describe('ImportMatchdayResultsComponent', () => {
  let component: ImportMatchdayResultsComponent;
  let fixture: ComponentFixture<ImportMatchdayResultsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ImportMatchdayResultsComponent]
    });
    fixture = TestBed.createComponent(ImportMatchdayResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
