package com.sorsix.gopbackend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sorsix.gopbackend.model.enumerations.Outcome
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Fixture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name="home_team_win_coefficient")
    val homeTeamWinCoefficient: Double,
    @Column(name="away_team_win_coefficient")
    val awayTeamWinCoefficient: Double,
    @Column(name="draw_coefficient")
    val drawCoefficient: Double,
    @Enumerated(value = EnumType.STRING)
    val outcome: Outcome?,
    @Column(name="start_time")
    val startTime: LocalDateTime,
    @Column(name="home_team_goals")
    val homeTeamGoals: Long?,
    @Column(name="away_team_goals")
    val awayTeamGoals: Long?,
    @ManyToOne
    @JoinColumn(name="home_team_id")
    val homeTeam: Team,
    @ManyToOne
    @JoinColumn(name="away_team_id")
    val awayTeam: Team,
    @ManyToOne
    @JoinColumn(name="matchday_id")
    @JsonIgnore
    val matchday: Matchday
)
