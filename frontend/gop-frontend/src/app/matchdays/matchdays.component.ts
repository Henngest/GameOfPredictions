import { Component } from '@angular/core';
import {Season} from "../interfaces/season";
import {SeasonsService} from "../seasons.service";
import {CompetitionsService} from "../competitions.service";
import {ActivatedRoute} from "@angular/router";
import {filter, forkJoin, map, mergeMap} from "rxjs";
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import {Matchday} from "../interfaces/matchday";
import {MatchdayService} from "../matchday.service";


@Component({
  selector: 'app-matchdays',
  templateUrl: './matchdays.component.html',
  styleUrls: ['./matchdays.component.css']
})
export class MatchdaysComponent {
  matchdays: Matchday[] | undefined;
  season: Season | undefined;
  loading: boolean = true;
  competitionId: string | undefined;
  faSpinner = faSpinner;

  constructor(private competitionService: CompetitionsService,
              private seasonsService: SeasonsService,
              private matchdayService: MatchdayService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.competitionId = params['competitionId'];
      }
    )
    this.route.paramMap.pipe(
      filter(params => params.has('seasonId')),
      map(params => params.get('seasonId')!!),
      mergeMap(param => {
        const seasonObs = this.seasonsService.getById(+param, +this.competitionId!!);
        const matchdayObs = this.matchdayService.getAll(+param);
        return forkJoin([matchdayObs, seasonObs]);
      })
    ).subscribe(
      ([matchdayData, seasonData]) => {
        this.season = seasonData;
        this.matchdays = matchdayData;
        console.log(this.matchdays);
        this.loading = false;
      }
    );
  }
}
