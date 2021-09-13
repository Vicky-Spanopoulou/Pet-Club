import { TestBed } from '@angular/core/testing';
import ExaminationRecordService from './examination-record.service';


describe('ExaminationRecordService', () => {
  let service: ExaminationRecordService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExaminationRecordService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
