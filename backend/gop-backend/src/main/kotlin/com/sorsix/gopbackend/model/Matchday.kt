package com.sorsix.gopbackend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Matchday(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "matchday_number")
    val matchdayNumber: Long,
    @Column(name = "start_time")
    val startTime: LocalDateTime,
    @Column(name = "is_finished")
    val isFinished: Boolean = false,
    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonIgnore
    val season: Season,
    @OneToMany(
        mappedBy = "matchday"
    ) // Number of queries executed is limited due to the number of
    // fixtures per matchday and number of matchdays per season
    val fixtures: List<Fixture>?
)
