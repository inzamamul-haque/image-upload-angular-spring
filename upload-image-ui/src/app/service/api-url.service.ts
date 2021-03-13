import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApiUrlService {

  public apiUrl = 'http://localhost:9001/';
  public uploadImage = this.apiUrl + 'image/upload';
  public imageUrl = this.apiUrl + 'images';

  constructor() { }
}
