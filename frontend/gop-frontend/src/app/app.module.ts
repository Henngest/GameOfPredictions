import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
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
import {ReactiveFormsModule} from "@angular/forms";
import {AuthInterceptor} from "./interceptors/auth-interceptor";
import { HeaderComponent } from './header/header.component';
import {JWT_OPTIONS, JwtModule} from "@auth0/angular-jwt";


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
    HeaderComponent,
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
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
