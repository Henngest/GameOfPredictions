import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Season} from "../interfaces/season";
import {faSpinner} from '@fortawesome/free-solid-svg-icons';
import {Matchday} from "../interfaces/matchday";
import {MatchdayService} from "../matchday.service";
import {DatePipe} from "@angular/common";
import {AuthenticationService} from "../authentication.service";

@Component({
  selector: 'app-matchdays',
  templateUrl: './matchdays.component.html',
  styleUrls: ['./matchdays.component.css'],
  providers: [DatePipe]
})
export class MatchdaysComponent implements OnChanges {

  @Input()
  season: Season | undefined;
  matchdays: Matchday[] | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;

  constructor(private matchdayService: MatchdayService,
              public authenticationService: AuthenticationService) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['season']) {
      this.matchdayService.getAllBySeasonId(this.season?.id!!)
        .subscribe(value => {
          this.matchdays = value;
          this.loading = false;
        })
    }
  }

}
