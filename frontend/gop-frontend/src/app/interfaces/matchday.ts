import {Season} from "./season";
import {Fixture} from "./fixture";

export interface Matchday {
  id: number,
  matchdayNumber: number,
  season: Season,
  startTime: Date,
  fixtures?: Fixture[]
}
