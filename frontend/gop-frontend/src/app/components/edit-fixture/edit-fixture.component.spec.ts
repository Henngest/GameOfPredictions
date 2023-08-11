import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFixtureComponent } from './edit-fixture.component';

describe('EditFixtureComponent', () => {
  let component: EditFixtureComponent;
  let fixture: ComponentFixture<EditFixtureComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditFixtureComponent]
    });
    fixture = TestBed.createComponent(EditFixtureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
