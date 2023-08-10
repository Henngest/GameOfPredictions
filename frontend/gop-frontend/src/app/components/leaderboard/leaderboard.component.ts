import {Component, OnInit} from '@angular/core';
import {Page} from "../../interfaces/page";
import {LeaderboardService} from "../../services/leaderboard.service";
import {ActivatedRoute} from "@angular/router";
import {mergeMap} from "rxjs";
import {DecimalPipe} from "@angular/common";
import {faSpinner, faTrophy} from '@fortawesome/free-solid-svg-icons';
import {countryList} from "../../CountryList";


@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css'],
  providers: [DecimalPipe]
})
export class LeaderboardComponent implements OnInit {

  page: Page | undefined;
  loading: boolean = true;
  faSpinner = faSpinner;
  faTrophy = faTrophy;
  countryList = countryList;

  constructor(private leaderboardService: LeaderboardService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.queryParamMap.pipe(
      mergeMap(params => {
        const page = params.has('page') ? +params.get('page')!! : 0;
        const size = params.has('size') ? +params.get('size')!! : 10;

        return this.leaderboardService.getUsersSortedByRating(page, size);
      })
    ).subscribe(value => {
      this.page = value;
      this.loading = false;
    })
  }

  getCountryCode(country: string): string {
    return countryList.find(c => c.name == country)?.code!!;
  }
}
