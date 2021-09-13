import { Component, Input, OnInit, OnChanges } from '@angular/core';
import { IExamRecord, IPet } from '../_models/models';
import ExaminationRecordService from 'src/app/_services/exam-record/examination-record.service';

@Component({
  selector: 'app-pet-exams',
  templateUrl: './pet-exams.component.html',
  styleUrls: ['./pet-exams.component.css', '../../assets/css/default.css', '../../assets/css/profile.css']
})
export class PetExamsComponent implements OnInit, OnChanges {

  exams1:boolean = true;

  @Input() pet:IPet;
  petExams:IExamRecord[];
  exams:IExamRecord[];

  @Input() examCategory='All';

  constructor(private examRecService: ExaminationRecordService
    ) { }

  ngOnInit(): void {
    this.getExams();
    this.filterExams();
  }

  ngOnChanges():void{
    this.filterExams();
  }

  filterExams():void{
    this.exams = this.petExams;
    if(this.examCategory&&this.examCategory !== 'All'){
      this.exams = this.exams.filter(x => x.examCat ===this.examCategory)
    }
  }

  toggleDescripion(event:any):void{
    let elem = document.getElementById(event);
    elem?.classList.toggle('hidden');
  }

  getExams():void{
    this.examRecService.getExamRecordByPet(this.pet.id).subscribe(
     (data)=> {
       this.petExams = data;
       this.filterExams();
     }
    );
  }

}
