import {Season} from "./season";

export interface Matchday {
  id: number,
  matchdayNumber: number,
  season: Season,
  startTime: Date
}
