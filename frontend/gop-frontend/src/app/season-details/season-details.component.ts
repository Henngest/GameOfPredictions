import {Component} from '@angular/core';
import {Season} from "../interfaces/season";
import {SeasonsService} from "../seasons.service";
import {ActivatedRoute} from "@angular/router";
import {filter, forkJoin, map, mergeMap} from "rxjs";
import {Competition} from "../interfaces/competition";
import {CompetitionsService} from "../competitions.service";
import {faSpinner} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-season-details',
  templateUrl: './season-details.component.html',
  styleUrls: ['./season-details.component.css']
})
export class SeasonDetailsComponent {

  season: Season | undefined;
  competition: Competition | undefined;
  seasonId: string | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;
  showAllMatchdays = false;

  constructor(private seasonService: SeasonsService,
              private competitionService: CompetitionsService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.seasonId = params['id'];
      }
    )
    this.route.paramMap.pipe(
      filter(params => params.has('competitionId')),
      map(params => params.get('competitionId')!!),
      mergeMap(param => {
        const seasonObs = this.seasonService.getById(+this.seasonId!!, +param); //TODO() Check if ids are a number
        const competitionObs = this.competitionService.getById(+param);
        return forkJoin([seasonObs, competitionObs]);
      })
    ).subscribe(
      ([seasonData, competitionData]) => {
        this.season = seasonData;
        this.competition = competitionData;
        this.loading = false;
      }
    );
  }

  onShowMatchdaysClick() {
    this.showAllMatchdays = true;
  }

}
