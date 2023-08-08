import {Component} from '@angular/core';
import {ImportMatchdaysService} from "../import-matchdays.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-import-matchdays',
  templateUrl: './import-matchdays.component.html',
  styleUrls: ['./import-matchdays.component.css']
})
export class ImportMatchdaysComponent {

  seasonId: string | undefined;
  selectedFile: File | null = null;
  errorMessage = '';

  constructor(private importMatchdaysService: ImportMatchdaysService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit() {
    this.route.params.subscribe(params =>
      this.seasonId = params['seasonId']
    )
  }

  onFormSubmit(event: Event) {
    event.preventDefault();
    if (this.selectedFile) {
      this.importMatchdaysService.importMatchdays(+this.seasonId!!, this.selectedFile)
        .subscribe(_ => {
          this.location.back();
        });
    } else {
      this.errorMessage = "You must select a file before submitting!";
    }
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

}
