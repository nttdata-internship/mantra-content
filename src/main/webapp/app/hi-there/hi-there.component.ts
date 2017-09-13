import {Component, OnInit} from '@angular/core';
import { Http, Response, URLSearchParams, Headers } from '@angular/http';
@Component({
  selector: 'jhi-hi-there',
  templateUrl: './hi-there.component.html',
  styleUrls: [
    'hi-there.css'
  ]
})
export class HiThereComponent implements OnInit {

  message: string;

  constructor(private http: Http) {
  
  
    this.message = 'HiThereComponent message';
  }

  ngOnInit() {
  }
  
  doUpload()
  {

		var formData = new FormData();
		
		//formData.append('file', document.getElementById('uploadFileInput').files[0]);
		
		
		var headers = new Headers()
            .set("X-CustomHeader", "custom header value");
		
		this.http.post('http://localhost:8080/content/upload', formData, { 
		    /*headers: {
		        'Content-Type': headers
		    },*/
		 
		});
		//.success(function (result) {alert('Stefan')}).error(function () {alert('Neacsu')});
		
		
		  
  }

}
