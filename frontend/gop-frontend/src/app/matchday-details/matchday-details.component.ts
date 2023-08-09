import {Component, OnInit} from '@angular/core';
import {Season} from "../interfaces/season";
import {Competition} from "../interfaces/competition";
import {SeasonsService} from "../seasons.service";
import {ActivatedRoute} from "@angular/router";
import {forkJoin, switchMap} from "rxjs";
import {faSpinner} from '@fortawesome/free-solid-svg-icons';
import {Matchday} from "../interfaces/matchday";
import {MatchdayService} from "../matchday.service";
import {DatePipe, DecimalPipe} from "@angular/common";
import {AuthenticationService} from "../authentication.service";

@Component({
  selector: 'app-matchday-details',
  templateUrl: './matchday-details.component.html',
  styleUrls: ['./matchday-details.component.css'],
  providers: [DatePipe, DecimalPipe]
})
export class MatchdayDetailsComponent implements OnInit {
  matchday: Matchday | undefined;
  season: Season | undefined;
  competition: Competition | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;

  constructor(private matchdayService: MatchdayService,
              private seasonsService: SeasonsService,
              private route: ActivatedRoute,
              public authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const competitionId = +params.get('competitionId')!!;
        const seasonId = +params.get('seasonId')!!;
        const matchdayId = +params.get('id')!!;

        const seasonRequest$ = this.seasonsService.getById(seasonId, competitionId);
        const matchdaysRequest$ = this.matchdayService.getById(matchdayId, seasonId);

        return forkJoin([seasonRequest$, matchdaysRequest$]);
      })
    ).subscribe(([seasonData, matchdayData]) => {
      this.matchday = matchdayData;
      this.season = seasonData;
      this.competition = this.season?.competition;
      this.loading = false;
    })
  }

  hasMatchdayStarted(): boolean {
    return new Date(this.matchday?.startTime!!).getTime() < Date.now();
  }
}
