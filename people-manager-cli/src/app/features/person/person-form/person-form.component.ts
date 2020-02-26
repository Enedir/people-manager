import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'sajadv-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.scss']
})
export class PersonFormComponent {
  
  previewUrl: string | ArrayBuffer = '';

  @Input() public formModel: FormGroup;

  loadImage(event: any) {
    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = () => {
        this.previewUrl = reader.result;
    }
  }
}
