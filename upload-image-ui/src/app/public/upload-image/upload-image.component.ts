import { Component, OnInit } from '@angular/core';
import {ImageService} from '../../service/image.service';
import {ApiUrlService} from '../../service/api-url.service';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.scss']
})
export class UploadImageComponent implements OnInit {
  imageUrl: any;
  fileToUpload: File = null;

  constructor(private imageService: ImageService, private global: ApiUrlService) { }

  ngOnInit(): void {
    this.imageUrl = '/assets/image/default-image.png';
  }
  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);

    // Show image preview
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    };
    reader.readAsDataURL(this.fileToUpload);
  }

  OnSubmit(Caption, Image) {
    this.imageService.postFile(Caption.value, this.fileToUpload).subscribe(
      data => {
        const url = this.global.imageUrl;
        console.log(data);
        Caption.value = null;
        Image.value = null;
        this.imageUrl = url + data.name;
      }
    );
  }
}
