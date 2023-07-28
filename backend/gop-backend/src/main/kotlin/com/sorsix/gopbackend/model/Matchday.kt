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
        @ManyToOne
        @JoinColumn(name = "season_id")
        @JsonIgnore
        val season: Season,
        @OneToMany(mappedBy = "matchday", fetch = FetchType.LAZY) // TODO: Make changes so that Hibernate doesn't ignore fetch type LAZY (issue is related to data classes).
        val fixtures: List<Fixture>?
)
