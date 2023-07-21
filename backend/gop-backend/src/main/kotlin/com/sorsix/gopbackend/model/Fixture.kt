package com.sorsix.gopbackend.model

import com.sorsix.gopbackend.model.enumerations.Outcome
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

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
    val outcome: Outcome,
    @Column(name="start_time")
    val startTime: LocalDateTime,
    @Column(name="home_team_goals")
    val homeTeamGoals: Long,
    @Column(name="away_team_goals")
    val awayTeamGoals: Long,
    @ManyToOne
    @JoinColumn(name="home_team_id")
    val homeTeam: Team,
    @ManyToOne
    @JoinColumn(name="away_team_id")
    val awayTeam: Team,
    @ManyToOne
    @JoinColumn(name="matchday_id")
    val matchday: Matchday
)
