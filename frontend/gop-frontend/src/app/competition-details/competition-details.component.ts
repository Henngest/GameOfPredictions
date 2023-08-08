import {Component, OnInit} from '@angular/core';
import {Competition} from "../interfaces/competition";
import {CompetitionsService} from "../competitions.service";
import {ActivatedRoute} from "@angular/router";
import {filter, map, mergeMap} from "rxjs";
import {faArrowDown, faArrowRight, faSpinner} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-competition-details',
  templateUrl: './competition-details.component.html',
  styleUrls: ['./competition-details.component.css']
})
export class CompetitionDetailsComponent implements OnInit {

  competition: Competition | undefined;
  loading = true;
  faSpinner = faSpinner;
  faRightArrow = faArrowRight;
  faDownArrow = faArrowDown;
  showAllSeasons = false;

  constructor(
    private competitionsService: CompetitionsService,
    private route: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params => params.has('id')),
      map(params => params.get('id')!!),
      mergeMap(param => this.competitionsService.getById(+param))
    ).subscribe(value => {
      this.competition = value;
      this.loading = false;
    })
  }

  onShowAllSeasonsClick() {
    this.showAllSeasons = !this.showAllSeasons;
  }
}
