import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {CompetitionsComponent} from './competitions/competitions.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { CompetitionDetailsComponent } from './competition-details/competition-details.component';
import { SeasonsComponent } from './seasons/seasons.component';
import { SeasonDetailsComponent } from './season-details/season-details.component';
import { MatchdaysComponent } from './matchdays/matchdays.component';
import { MatchdayDetailsComponent } from './matchday-details/matchday-details.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    CompetitionsComponent,
    CompetitionDetailsComponent,
    SeasonsComponent,
    SeasonDetailsComponent,
    MatchdaysComponent,
    MatchdayDetailsComponent,
    LoginComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
