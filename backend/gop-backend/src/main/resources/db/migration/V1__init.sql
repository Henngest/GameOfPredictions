create table competition
(
    id            bigserial primary key,
    name          text not null,
    short_history text
);
create table season
(
    id             bigserial primary key,
    name           text not null,
    competition_id bigint references competition on delete cascade on update cascade
);
create table matchday
(
    id              bigserial primary key,
    matchday_number bigint,
    season_id       bigint references season on delete cascade on update cascade,
    start_time      timestamp not null,
    is_finished     boolean default false
);
create table team
(
    name text primary key,
    logo text not null
);
create table fixture
(
    id                        bigserial primary key,
    home_team_id              name references team on delete cascade on update cascade,
    away_team_id              name references team on delete cascade on update cascade,
    matchday_id               bigint references matchday on delete cascade on update cascade,
    home_team_win_coefficient real      not null,
    away_team_win_coefficient real      not null,
    draw_coefficient          real      not null,
    outcome                   text,
    start_time                timestamp not null,
    home_team_goals           bigint,
    away_team_goals           bigint
);
create table users
(
    username text primary key,
    password text not null,
    rating   real default 0.0,
    role     text not null,
    country  text not null
);
create table prediction
(
    id                bigserial primary key,
    user_id           text references users on delete cascade on update cascade,
    fixture_id        bigint references fixture on delete cascade on update cascade,
    predicted_outcome text not null
);
create table team_seasons
(
    id        bigserial primary key,
    team_id   text references team on delete cascade on update cascade,
    season_id bigint references season on delete cascade on update cascade
);