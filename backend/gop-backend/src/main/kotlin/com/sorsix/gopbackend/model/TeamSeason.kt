package com.sorsix.gopbackend.model

import jakarta.persistence.*

@Entity
@Table(name="team_seasons")
data class TeamSeason(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name="team_id")
    val team: Team,
    @ManyToOne
    @JoinColumn(name="season_id")
    val season: Season
)
