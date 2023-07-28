import { Component } from '@angular/core';
import {Season} from "../interfaces/season";
import {SeasonsService} from "../seasons.service";
import {ActivatedRoute} from "@angular/router";
import {filter, forkJoin, map, mergeMap} from "rxjs";
import {CompetitionsService} from "../competitions.service";
import {Competition} from "../interfaces/competition";
import { faSpinner } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-seasons',
  templateUrl: './seasons.component.html',
  styleUrls: ['./seasons.component.css']
})
export class SeasonsComponent {

  seasons: Season[] | undefined;
  competition: Competition | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;

  constructor(private seasonsService: SeasonsService,
              private competitionService: CompetitionsService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params => params.has('id')),
      map(params => params.get('id')!!),
      mergeMap(param => {
        const competitionObs = this.competitionService.getById(+param);
        const seasonObs = this.seasonsService.getAll(+param);
        return forkJoin([seasonObs, competitionObs]);
      })
    ).subscribe(
      ([seasonData, competitionData]) => {
        this.competition = competitionData;
        this.seasons = seasonData;
        this.loading = false;
      }
    );
  }
}
