import {Component, OnInit} from '@angular/core';
import {CompetitionsService} from "../../services/competitions.service";
import {Competition} from "../../interfaces/competition";
import { faFutbol } from '@fortawesome/free-solid-svg-icons';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent implements OnInit {

  competitions: Competition[] | undefined;
  loading = true;
  faFutbol = faFutbol;
  faSpinner = faSpinner;

  constructor(
    private competitionsService: CompetitionsService
  ) {
  }

  ngOnInit(): void {
    this.competitionsService.getAll().subscribe(value => {
      this.competitions = value;
      this.loading = false;
    })
  }

}
