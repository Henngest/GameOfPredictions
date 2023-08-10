import {Component, OnInit} from '@angular/core';
import {Season} from "../../interfaces/season";
import {SeasonsService} from "../../services/seasons.service";
import {ActivatedRoute} from "@angular/router";
import {filter, forkJoin, map, mergeMap} from "rxjs";
import {Competition} from "../../interfaces/competition";
import {CompetitionsService} from "../../services/competitions.service";
import {faArrowDown, faArrowRight, faSpinner} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-season-details',
  templateUrl: './season-details.component.html',
  styleUrls: ['./season-details.component.css']
})
export class SeasonDetailsComponent implements OnInit{

  season: Season | undefined;
  competition: Competition | undefined;
  seasonId: string | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;
  faRightArrow = faArrowRight;
  faDownArrow = faArrowDown;
  showAllMatchdays = false;

  constructor(private seasonService: SeasonsService,
              private competitionService: CompetitionsService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params => params.has('competitionId') && params.has('id')),
      map(params => {
        return {
          competitionId: +params.get('competitionId')!!,
          seasonId: +params.get('id')!!
        }
      }),
      filter(({competitionId, seasonId}) => !isNaN(competitionId) && !isNaN(seasonId)),
      mergeMap(param => {
        const seasonObs = this.seasonService.getById(param.seasonId, param.competitionId); //TODO() Check if ids are a number
        const competitionObs = this.competitionService.getById(param.competitionId);
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
    this.showAllMatchdays = !this.showAllMatchdays;
  }

}
