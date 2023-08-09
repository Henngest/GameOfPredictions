import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ImportMatchdayResultsService} from "../import-matchday-results.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-import-matchday-results',
  templateUrl: './import-matchday-results.component.html',
  styleUrls: ['./import-matchday-results.component.css']
})
export class ImportMatchdayResultsComponent {

  seasonId: string | undefined;
  matchdayId: string | undefined;
  selectedFile: File | null = null;
  errorMessage = '';

  constructor(private importMatchdayResultsService: ImportMatchdayResultsService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
        this.seasonId = params['seasonId'];
        this.matchdayId = params['id'];
      }
    )
  }

  onFormSubmit(event: Event) {
    event.preventDefault();
    if (this.selectedFile) {
      this.importMatchdayResultsService.importMatchdayResults(+this.seasonId!!, +this.matchdayId!!, this.selectedFile)
        .subscribe(_ => {
          this.location.back();
        });
    } else {
      this.errorMessage = "You must select a file before submitting!";
    }
  }

  // TODO: Create a button with onSubmit event for importing results, not like this!
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }
}
