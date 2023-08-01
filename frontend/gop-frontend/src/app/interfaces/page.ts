import {User} from "./user";

export interface Page {
  content: User[],
  totalPages: number,
  first: boolean,
  last: boolean,
  number: number,
  size: number
}
