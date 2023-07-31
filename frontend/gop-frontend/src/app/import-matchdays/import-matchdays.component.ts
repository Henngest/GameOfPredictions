import { Component } from '@angular/core';

@Component({
  selector: 'app-import-matchdays',
  templateUrl: './import-matchdays.component.html',
  styleUrls: ['./import-matchdays.component.css']
})
export class ImportMatchdaysComponent {
  
  onFileSelected(event: any) {
    const file = event.target.files[0];
    console.log(file);
  }

}
