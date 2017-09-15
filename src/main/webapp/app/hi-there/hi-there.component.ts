import {Component, OnInit} from '@angular/core';
import { Http, Response, Headers, URLSearchParams,RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';
@Injectable()
@Component({
  selector: 'jhi-hi-there',
  templateUrl: './hi-there.component.html',
  styleUrls: [
    'hi-there.css'
  ]
})
export class HiThereComponent implements OnInit {

  message: string;


  constructor(private http: Http, private alertService: JhiAlertService,) {
  
  
  }

  ngOnInit() {
  }
  
  doUpload($event) {
	  
	  var files = $event.target.files || $event.srcElement.files;
	  var file = files[0];
      
      let formData:FormData  = new FormData();
      formData.append('file', file, file.name);
      let headers = new Headers();
      headers.append('Access-Control-Allow-Origin','*');
      headers.append('Access-Control-Allow-Methods','GET, POST, PATCH, PUT, DELETE, OPTIONS');
      headers.append('Access-Control-Allow-Headers','Origin, Content-Type, X-Auth-Token');
      let options = new RequestOptions({ headers:headers });
      let url='localhost:8080\\content\\upload';
      this.http.post('http:\\\\localhost:8080\\content\\upload', formData,options) .subscribe(data => {
    	  this.alertService.error("1","2", null);
      }, error => {
    	  console.log(formData);
          console.log(JSON.stringify(error.json()));
      });
    
		  
  }

}
