import {Component, OnInit} from '@angular/core';
import {DatePipe, DecimalPipe} from "@angular/common";
import {Matchday} from "../interfaces/matchday";
import {Season} from "../interfaces/season";
import {Competition} from "../interfaces/competition";
import {faSpinner} from '@fortawesome/free-solid-svg-icons';
import {MatchdayService} from "../matchday.service";
import {SeasonsService} from "../seasons.service";
import {ActivatedRoute, Router} from "@angular/router";
import {forkJoin, switchMap} from "rxjs";
import {Prediction} from "../interfaces/prediction";
import {AuthenticationService} from "../authentication.service";
import {Outcome} from "../enums/outcome";
import {PredictionsService} from "../predictions.service";

@Component({
  selector: 'app-make-predictions',
  templateUrl: './make-predictions.component.html',
  styleUrls: ['./make-predictions.component.css'],
  providers: [DatePipe, DecimalPipe]
})
export class MakePredictionsComponent implements OnInit {

  matchday: Matchday | undefined;
  season: Season | undefined;
  competition: Competition | undefined;
  predictions: Prediction[] | undefined;
  outcome = Outcome;
  loading: boolean = true;
  errorMessage: string | undefined;
  faSpinner = faSpinner;

  constructor(private matchdayService: MatchdayService,
              private seasonsService: SeasonsService,
              private route: ActivatedRoute,
              private authService: AuthenticationService,
              private predictionsService: PredictionsService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const competitionId = +params.get('competitionId')!!;
        const seasonId = +params.get('seasonId')!!;
        const matchdayId = +params.get('id')!!;

        const seasonRequest$ = this.seasonsService.getById(seasonId, competitionId);
        const matchdaysRequest$ = this.matchdayService.getById(matchdayId, seasonId);
        const predictionsRequest$ = this.predictionsService.getPredictionsForMatchdayByUser(matchdayId, this.authService.getUsername()!)

        return forkJoin([seasonRequest$, matchdaysRequest$, predictionsRequest$]);
      })
    ).subscribe(([seasonData, matchdayData, predictionsData]) => {
      this.matchday = matchdayData;
      this.season = seasonData;
      this.competition = this.season?.competition;
      this.loading = false;
      if (predictionsData.length > 0) {
        this.predictions = predictionsData;
      } else {
        this.initPredictions();
      }
    });
  }

  initPredictions(): void {
    const username = this.authService.getUsername();
    this.predictions = this.matchday?.fixtures?.map(fixture => {
      return {userId: username!!, fixtureId: fixture.id};
    });
  }

  hasMatchdayStarted(): boolean {
    return new Date(this.matchday?.startTime!!).getTime() < Date.now();
  }

  onPredictionChange(fixtureId: number, selectedOutcome: Outcome): void {
    if (this.hasMatchdayStarted()) {
      return;
    }
    this.predictions = this.predictions?.map(prediction => {
      if (prediction.fixtureId == fixtureId)
        return {...prediction, predictedOutcome: selectedOutcome};

      return prediction;
    });
  }

  isPredictionSelected(fixtureId: number, selectedOutcome: Outcome): boolean {
    return !!this.predictions?.some(p => p.fixtureId == fixtureId && p.predictedOutcome === selectedOutcome);
  }

  onSubmit(): void {
    if (!this.predictions || this.predictions?.some(p => p.predictedOutcome === undefined)) {
      this.errorMessage = "Please make a prediction for all fixtures."
    } else {
      this.predictionsService.submitPredictions(this.predictions).subscribe(value => {
        this.router.navigateByUrl(`competitions/${this.competition?.id}/seasons/${this.season?.id}/matchdays`);
      })
    }
  }

}
