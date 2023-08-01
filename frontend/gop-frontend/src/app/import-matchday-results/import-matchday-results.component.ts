import {Component} from '@angular/core';
import {ImportMatchdaysService} from "../import-matchdays.service";
import {ActivatedRoute} from "@angular/router";
import {ImportMatchdayResultsService} from "../import-matchday-results.service";

@Component({
  selector: 'app-import-matchday-results',
  templateUrl: './import-matchday-results.component.html',
  styleUrls: ['./import-matchday-results.component.css']
})
export class ImportMatchdayResultsComponent {

  seasonId: string | undefined;
  matchdayId: string | undefined;

  constructor(private importMatchdayResultsService: ImportMatchdayResultsService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
        this.seasonId = params['seasonId'];
        this.matchdayId = params['id'];
      }
    )
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.importMatchdayResultsService.importMatchdayResults(+this.seasonId!!, +this.matchdayId!!, file).subscribe()
  }
}
