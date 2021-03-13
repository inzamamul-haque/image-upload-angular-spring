import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {ApiUrlService} from './api-url.service';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient, private apiUrl: ApiUrlService) { }

  postFile(caption: string, file: File): any {
    const formData = new FormData();
    formData.append('file', file);
    const url = this.apiUrl.uploadImage;
    return this.http.post(url, formData).pipe(map((res: any) => {
      return res;
    }));
  }
}
