package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Season
import org.springframework.data.jpa.repository.JpaRepository

interface SeasonRepository : JpaRepository<Season, Long> {
}