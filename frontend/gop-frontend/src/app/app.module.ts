import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {CompetitionsComponent} from './components/competitions/competitions.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { CompetitionDetailsComponent } from './components/competition-details/competition-details.component';
import { SeasonsComponent } from './components/seasons/seasons.component';
import { SeasonDetailsComponent } from './components/season-details/season-details.component';
import { MatchdaysComponent } from './components/matchdays/matchdays.component';
import { MatchdayDetailsComponent } from './components/matchday-details/matchday-details.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import {ReactiveFormsModule} from "@angular/forms";
import {AuthInterceptor} from "./interceptors/auth-interceptor";
import { ImportMatchdaysComponent } from './components/import-matchdays/import-matchdays.component';
import { HeaderComponent } from './components/header/header.component';
import {JWT_OPTIONS, JwtModule} from "@auth0/angular-jwt";
import { MakePredictionsComponent } from './components/make-predictions/make-predictions.component';
import { ImportMatchdayResultsComponent } from './components/import-matchday-results/import-matchday-results.component';
import { LeaderboardComponent } from './components/leaderboard/leaderboard.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import {ResponseErrorInterceptor} from "./interceptors/response-error-interceptor";
import { ErrorComponent } from './components/error/error.component';


export function jwtOptionsFactory() {
  return {
    tokenGetter: () => {
      return localStorage.getItem('jwt');
    },
    allowedDomains: [],
    disallowedRoutes: [],
  };
}

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
    ImportMatchdaysComponent,
    HeaderComponent,
    MakePredictionsComponent,
    ImportMatchdayResultsComponent,
    LeaderboardComponent,
    UserProfileComponent,
    EditProfileComponent,
    SidebarComponent,
    ErrorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    JwtModule.forRoot({
      jwtOptionsProvider: {
        provide: JWT_OPTIONS,
        useFactory: jwtOptionsFactory,
      },
    }),
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ResponseErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
