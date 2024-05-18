import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAnswersComponent } from './add-answers.component';

describe('AddAnswersComponent', () => {
  let component: AddAnswersComponent;
  let fixture: ComponentFixture<AddAnswersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddAnswersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddAnswersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
