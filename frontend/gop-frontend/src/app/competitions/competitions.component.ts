import {Component, OnInit} from '@angular/core';
import {CompetitionsService} from "../competitions.service";
import {Competition} from "../interfaces/competition";
import {Observable} from "rxjs";
import { faFutbol } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent implements OnInit {

  competitions$: Observable<Competition[]> | undefined;
  faFutbol = faFutbol;

  constructor(
    private competitionsService: CompetitionsService
  ) {
  }

  ngOnInit(): void {
    this.competitions$ = this.competitionsService.getAll()
  }

}
