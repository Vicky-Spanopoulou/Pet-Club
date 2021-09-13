import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { Editor, schema } from 'ngx-editor';
import { PetExamsComponent } from 'src/app/pet-exams/pet-exams.component';
import { IExamRecord } from 'src/app/_models/models';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import ExaminationRecordService from 'src/app/_services/exam-record/examination-record.service';
import { toHTML, toDoc } from 'ngx-editor'; 

@Component({
  selector: 'app-dash-pet-history',
  templateUrl: './dash-pet-history.component.html',
    
  styleUrls: ['./dash-pet-history.component.css',
              '../../dash-users/dash-users.component.css',
              '../../../../assets/css/dashboardCss/DashboardForms.css']
})
export class DashPetHistoryComponent implements OnInit,OnChanges {

  editor: Editor;
  diagnosis:any;
  newExamination:any={
    id:null,
    examCat:null,
    examDay: new Date(),
    diagnosis:null,
    pet:null,
    user:null,
    validUntil:null
  };

  @Input() dtoPet:any; 
  @Output() notify = new EventEmitter<any>();

  @ViewChild('history',{static:false})
  examsComponent:PetExamsComponent;

  exams:IExamRecord[];
  examCategories:any[];
  constructor(private examCategoriesService:CategoriesService,private examinationService:ExaminationRecordService) { 
      this.editor = new Editor();
    }
  ngOnChanges() {
    
  }

  ngOnInit(): void {
    this.newExamination.diagnosis = toDoc(this.newExamination.diagnosis);
    this.getAllExamCategories();
    this.getPetExaminations();
  }

  getAllExamCategories(){
    this.examCategoriesService.getAllExamCategories().subscribe((actualyExamCategories:any[]) =>{
      this.examCategories = actualyExamCategories;
    });
  }

  getPetExaminations(){
    this.examinationService.getExamRecordByPet(this.dtoPet.id).subscribe((actualyExams:IExamRecord[]) =>{
      this.exams = actualyExams;
    });
  }

  loadExams(){
    this.examsComponent.getExams();
  }

  submitExamForm(): void {   
    this.newExamination.pet = this.dtoPet.id;
    this.newExamination.user = this.dtoPet.ownerId;
    if(this.newExamination.diagnosis){   
      this.newExamination.diagnosis = toHTML(this.newExamination.diagnosis, schema);
    }
      this.examinationService.createExamRecord(this.newExamination).subscribe(
        (exam: IExamRecord) => {
          this.newExamination.id = exam.id;
          this.notify.emit();
        },
        (error) => null
      );
      //close exam form
      window.scrollTo(0, 0);
  }
 
}
