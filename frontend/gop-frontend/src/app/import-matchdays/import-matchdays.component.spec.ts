import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportMatchdaysComponent } from './import-matchdays.component';

describe('ImportMatchdaysComponent', () => {
  let component: ImportMatchdaysComponent;
  let fixture: ComponentFixture<ImportMatchdaysComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ImportMatchdaysComponent]
    });
    fixture = TestBed.createComponent(ImportMatchdaysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
