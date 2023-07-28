import {Team} from "./team";
import {Outcome} from "../enums/outcome";

export interface Fixture {
  id: number;
  homeTeamWinCoefficient: number;
  awayTeamWinCoefficient: number;
  drawCoefficient: number;
  outcome?: Outcome;
  startTime: string;
  homeTeamGoals?: number;
  awayTeamGoals?: number;
  homeTeam: Team;
  awayTeam: Team;
}
