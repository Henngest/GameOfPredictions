import { Component } from '@angular/core';
import {Season} from "../interfaces/season";
import {SeasonsService} from "../seasons.service";
import {CompetitionsService} from "../competitions.service";
import {ActivatedRoute} from "@angular/router";
import {filter, forkJoin, switchMap} from "rxjs";
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import {Matchday} from "../interfaces/matchday";
import {MatchdayService} from "../matchday.service";
import {DatePipe} from "@angular/common";


@Component({
  selector: 'app-matchdays',
  templateUrl: './matchdays.component.html',
  styleUrls: ['./matchdays.component.css'],
  providers: [DatePipe]
})
export class MatchdaysComponent {
  matchdays: Matchday[] | undefined;
  season: Season | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;

  constructor(private competitionService: CompetitionsService,
              private seasonsService: SeasonsService,
              private matchdayService: MatchdayService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params => params.has('competitionId') && params.has('seasonId')),
      switchMap(params => {
        const competitionId = +params.get('competitionId')!!;
        const seasonId = +params.get('seasonId')!!;

        const seasonRequest$ = this.seasonsService.getById(seasonId, competitionId);
        const matchdaysRequest$ = this.matchdayService.getAll(seasonId);

        return forkJoin([seasonRequest$, matchdaysRequest$]);
      })
    ).subscribe(([seasonData, matchdaysData]) => {
      this.season = seasonData;
      this.matchdays = matchdaysData;
      this.loading = false;
    })
  }
}
