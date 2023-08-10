import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Season} from "../../interfaces/season";
import {SeasonsService} from "../../services/seasons.service";
import {faSpinner} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-seasons',
  templateUrl: './seasons.component.html',
  styleUrls: ['./seasons.component.css']
})
export class SeasonsComponent implements OnChanges {

  @Input()
  competitionId: number | undefined;
  seasons: Season[] | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;

  constructor(private seasonsService: SeasonsService
  ) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['competitionId']) {
      this.seasonsService.getAllByCompetitionId(this.competitionId!!)
        .subscribe(value => {
          this.seasons = value;
          this.loading = false;
        });
    }
  }

}
