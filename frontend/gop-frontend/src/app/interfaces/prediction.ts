import {Outcome} from "../enums/outcome";

export interface Prediction {
  predictedOutcome?: Outcome,
  userId: string,
  fixtureId: number
}
