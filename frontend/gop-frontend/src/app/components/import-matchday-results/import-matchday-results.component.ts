import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {MatchdayService} from "../../services/matchday.service";

@Component({
  selector: 'app-import-matchday-results',
  templateUrl: './import-matchday-results.component.html',
  styleUrls: ['./import-matchday-results.component.css']
})
export class ImportMatchdayResultsComponent implements OnInit {

  seasonId: string | undefined;
  matchdayId: string | undefined;
  selectedFile: File | null = null;
  errorMessage = '';

  constructor(private matchdayService: MatchdayService,
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
      this.matchdayService.importMatchdayResults(+this.seasonId!!, +this.matchdayId!!, this.selectedFile)
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
