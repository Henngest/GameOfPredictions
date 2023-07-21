package com.sorsix.gopbackend.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Matchday(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name="matchday_number")
    val matchdayNumber: Long,
    @Column(name="start_time")
    val startTime: LocalDateTime,
    @ManyToOne
    @JoinColumn(name="season_id")
    val season: Season
)
