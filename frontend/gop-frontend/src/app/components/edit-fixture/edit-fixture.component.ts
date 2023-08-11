import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {FixtureService} from "../../services/fixture.service";
import {Fixture} from "../../interfaces/fixture";
import {filter, mergeMap} from "rxjs";

@Component({
  selector: 'app-edit-fixture',
  templateUrl: './edit-fixture.component.html',
  styleUrls: ['./edit-fixture.component.css']
})
export class EditFixtureComponent implements OnInit {
  fixture: Fixture | undefined;
  matchdayId: number | undefined;
  errorMsg: boolean = false;
  returnUrl = '';

  editForm: FormGroup = new FormGroup({
    startTime: new FormControl('', [Validators.required]),
    homeTeamWinCoefficient: new FormControl('', [Validators.required, Validators.min(1)]),
    awayTeamWinCoefficient: new FormControl('', [Validators.required, Validators.min(1)]),
    drawCoefficient: new FormControl('', [Validators.required, Validators.min(1)]),
  });

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private fixtureService: FixtureService) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params => params.has('matchdayId') && params.has('id')),
      mergeMap(params => {
        const matchdayId = +params.get('matchdayId')!!;
        const fixtureId = +params.get('id')!!;
        this.matchdayId = matchdayId;
        this.returnUrl = params.has('returnUrl') ? decodeURIComponent(params.get('returnUrl')!!) : '';

        return this.fixtureService.getById(matchdayId, fixtureId);
      })
    ).subscribe(
      value => {
        this.editForm.setValue({
          startTime: value?.startTime,
          homeTeamWinCoefficient: value?.homeTeamWinCoefficient.toPrecision(3),
          awayTeamWinCoefficient: value?.awayTeamWinCoefficient.toPrecision(3),
          drawCoefficient: value?.drawCoefficient.toPrecision(3)
        })
        this.fixture = value;
      }
    )
  }

  submit() {
    if (!this.editForm.invalid) {
      this.fixtureService.editFixture(this.matchdayId!!,
        this.fixture?.id!!,
        this.editForm.get('startTime')?.value,
        this.editForm.get('homeTeamWinCoefficient')?.value,
        this.editForm.get('drawCoefficient')?.value,
        this.editForm.get('awayTeamWinCoefficient')?.value)
        .subscribe(
          param => {
            this.router.navigateByUrl(`/competitions/${this.fixture?.matchday.season.competition.id}/seasons/${this.fixture?.matchday.season.id}/matchdays/${this.matchdayId}`);
          }
        );
    } else {
      this.errorMsg = true;
    }

  }
}
