import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompetitionsComponent} from "./competitions/competitions.component";
import {CompetitionDetailsComponent} from "./competition-details/competition-details.component";
import {SeasonsComponent} from "./seasons/seasons.component";
import {SeasonDetailsComponent} from "./season-details/season-details.component";
import {MatchdaysComponent} from "./matchdays/matchdays.component";
import {MatchdayDetailsComponent} from "./matchday-details/matchday-details.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ImportMatchdaysComponent} from "./import-matchdays/import-matchdays.component";
import {MakePredictionsComponent} from "./make-predictions/make-predictions.component";
import {ImportMatchdayResultsComponent} from "./import-matchday-results/import-matchday-results.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";

const routes: Routes = [
  {path: 'competitions', component: CompetitionsComponent},
  {path: 'competitions/:id', component: CompetitionDetailsComponent},
  {path: 'competitions/:id/seasons', component: SeasonsComponent},
  {path: 'competitions/:competitionId/seasons/:id', component: SeasonDetailsComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays', component: MatchdaysComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays/import', component: ImportMatchdaysComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays/:id', component: MatchdayDetailsComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays/:id/predict', component: MakePredictionsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays/:id/importResults', component: ImportMatchdayResultsComponent},
  {path: 'profile', component: UserProfileComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
