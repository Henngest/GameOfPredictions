import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompetitionsComponent} from "./competitions/competitions.component";
import {CompetitionDetailsComponent} from "./competition-details/competition-details.component";

const routes: Routes = [
  {path: 'competitions', component: CompetitionsComponent},
  {path: 'competitions/:id', component: CompetitionDetailsComponent},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
