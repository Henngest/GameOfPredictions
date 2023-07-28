import { Component } from '@angular/core';
import {Season} from "../interfaces/season";
import {Competition} from "../interfaces/competition";
import {SeasonsService} from "../seasons.service";
import {CompetitionsService} from "../competitions.service";
import {ActivatedRoute} from "@angular/router";
import {filter, forkJoin, map, mergeMap, switchMap} from "rxjs";
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import {Matchday} from "../interfaces/matchday";
import {MatchdayService} from "../matchday.service";
import {DatePipe, DecimalPipe} from "@angular/common";

@Component({
  selector: 'app-matchday-details',
  templateUrl: './matchday-details.component.html',
  styleUrls: ['./matchday-details.component.css'],
  providers: [DatePipe, DecimalPipe]
})
export class MatchdayDetailsComponent {
  matchday: Matchday | undefined;
  season: Season | undefined;
  competition: Competition | undefined;
  seasonId: string | undefined;
  competitionId: string | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;

  constructor(private matchdayService: MatchdayService,
              private seasonsService: SeasonsService,
              private competitionService: CompetitionsService,
              private route: ActivatedRoute) {
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

      console.log(this.matchday)
    })

  }
}
