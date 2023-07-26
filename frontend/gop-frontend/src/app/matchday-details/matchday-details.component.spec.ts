import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchdayDetailsComponent } from './matchday-details.component';

describe('MatchdayDetailsComponent', () => {
  let component: MatchdayDetailsComponent;
  let fixture: ComponentFixture<MatchdayDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MatchdayDetailsComponent]
    });
    fixture = TestBed.createComponent(MatchdayDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
