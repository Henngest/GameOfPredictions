create table competition(
    id bigserial primary key,
    name text not null,
    short_history text
);
create table season(
  id bigserial primary key,
  name text not null,
  competition_id bigint references competition
);
create table matchday(
    id bigserial primary key,
    matchday_number bigint,
    season_id bigint references season,
    start_time timestamp not null
);
create table team(
    name text primary key,
    logo text not null
);
create table fixture(
    id bigserial primary key,
    home_team_id name references team,
    away_team_id name references team,
    matchday_id bigint references matchday,
    home_team_win_coefficient real not null,
    away_team_win_coefficient real not null,
    draw_coefficient real not null,
    outcome text,
    start_time timestamp not null
);
create table users(
    username text primary key,
    password text not null,
    rating real,
    role text not null
);
create table prediction(
    id bigserial primary key,
    user_id text references users,
    fixture_id bigint references fixture,
    predicted_outcome text not null
);