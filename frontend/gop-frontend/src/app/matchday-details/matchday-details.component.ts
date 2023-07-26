import { Component } from '@angular/core';
import {Season} from "../interfaces/season";
import {Competition} from "../interfaces/competition";
import {SeasonsService} from "../seasons.service";
import {CompetitionsService} from "../competitions.service";
import {ActivatedRoute} from "@angular/router";
import {filter, forkJoin, map, mergeMap} from "rxjs";
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import {Matchday} from "../interfaces/matchday";
import {MatchdayService} from "../matchday.service";

@Component({
  selector: 'app-matchday-details',
  templateUrl: './matchday-details.component.html',
  styleUrls: ['./matchday-details.component.css']
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
              private seasonService: SeasonsService,
              private competitionService: CompetitionsService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void{
    this.route.params.subscribe(
      params => {
        this.competitionId = params['competitionId'];
        this.seasonId = params['seasonId'];
      }
    )
    this.route.paramMap.pipe(
      filter(params => params.has('id')),
      map(params => params.get('id')!!),
      mergeMap(param => {
        const matchdayObs = this.matchdayService.getById(+param,+this.seasonId!!);
        const seasonObs = this.seasonService.getById(+this.seasonId!!, +this.competitionId!!); //TODO() Check if ids are a number
        const competitionObs = this.competitionService.getById(+this.competitionId!!);
        return forkJoin([matchdayObs, seasonObs, competitionObs]);
      })
    ).subscribe(
      ([matchdayData, seasonData, competitionData]) => {
        this.matchday = matchdayData;
        this.season = seasonData;
        this.competition = competitionData;
        this.loading = false;
      }
    );
  }
}
