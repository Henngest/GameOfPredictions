package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Competition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompetitionRepository: JpaRepository<Competition, Long> {
}