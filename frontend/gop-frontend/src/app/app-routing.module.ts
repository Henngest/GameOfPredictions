import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompetitionsComponent} from "./competitions/competitions.component";
import {CompetitionDetailsComponent} from "./competition-details/competition-details.component";
import {SeasonsComponent} from "./seasons/seasons.component";
import {SeasonDetailsComponent} from "./season-details/season-details.component";
import {MatchdaysComponent} from "./matchdays/matchdays.component";

const routes: Routes = [
  {path: 'competitions', component: CompetitionsComponent},
  {path: 'competitions/:id', component: CompetitionDetailsComponent},
  {path: 'competitions/:id/seasons', component: SeasonsComponent},
  {path: 'competitions/:competitionId/seasons/:id', component: SeasonDetailsComponent},
  {path: 'competitions/:competitionId/seasons/:seasonId/matchdays', component: MatchdaysComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
