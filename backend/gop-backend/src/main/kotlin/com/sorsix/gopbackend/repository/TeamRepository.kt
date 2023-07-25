package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, String> {
}