import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TerrariumFormComponent } from './terrarium-form.component';

describe('TerrariumFormComponent', () => {
  let component: TerrariumFormComponent;
  let fixture: ComponentFixture<TerrariumFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TerrariumFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TerrariumFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
