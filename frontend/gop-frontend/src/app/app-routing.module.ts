import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompetitionsComponent} from "./competitions/competitions.component";
import {CompetitionDetailsComponent} from "./competition-details/competition-details.component";
import {SeasonDetailsComponent} from "./season-details/season-details.component";
import {MatchdaysComponent} from "./matchdays/matchdays.component";
import {MatchdayDetailsComponent} from "./matchday-details/matchday-details.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ImportMatchdaysComponent} from "./import-matchdays/import-matchdays.component";
import {MakePredictionsComponent} from "./make-predictions/make-predictions.component";
import {ImportMatchdayResultsComponent} from "./import-matchday-results/import-matchday-results.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {EditProfileComponent} from "./edit-profile/edit-profile.component";
import {LeaderboardComponent} from "./leaderboard/leaderboard.component";
import {IsAdminGuard} from "./guards/is-admin.guard";
import {IsAuthenticatedGuard} from "./guards/is-authenticated.guard";
import {ErrorComponent} from "./error/error.component";

const routes: Routes = [
  {path: 'competitions', component: CompetitionsComponent},
  {path: '', redirectTo: '/competitions', pathMatch: 'full'},
  {path: 'competitions/:id', component: CompetitionDetailsComponent},
  {path: 'competitions/:competitionId/seasons/:id', component: SeasonDetailsComponent},
  // {path: 'competitions/:competitionId/seasons/:seasonId/matchdays', component: MatchdaysComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays/import', component: ImportMatchdaysComponent, canActivate: [IsAdminGuard], data: { requiredRoles: ['ROLE_ADMIN'] }},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays/:id', component: MatchdayDetailsComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays/:id/predict', component: MakePredictionsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays/:id/importResults', component: ImportMatchdayResultsComponent, canActivate: [IsAdminGuard], data: { requiredRoles: ['ROLE_ADMIN'] }},
  {path: 'profile', component: UserProfileComponent, canActivate: [IsAuthenticatedGuard]},
  {path: 'profile/edit', component: EditProfileComponent, canActivate: [IsAuthenticatedGuard]},
  {path: 'leaderboard', component: LeaderboardComponent},
  {path: 'error', component: ErrorComponent},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
