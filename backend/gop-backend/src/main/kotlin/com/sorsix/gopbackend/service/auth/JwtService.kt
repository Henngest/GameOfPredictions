package com.sorsix.gopbackend.service.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function
import kotlin.collections.HashMap

@Service
class JwtService {

    @Value("\${secret.key}")
    private val SECRET_KEY: String? = null

    fun generateToken(
        extraClaims: Map<String, Any> = HashMap(),
        userDetails: UserDetails
    ): String {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis().plus(1000 * 60 * 60 * 24 * 14)))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun isTokenValid(jwt: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(jwt)
        return username == userDetails.username && !isTokenExpired(jwt)
    }

    fun isTokenExpired(jwt: String): Boolean {
        return extractExpiration(jwt).before(Date())
    }

    fun extractExpiration(jwt: String): Date {
        return extractClaim(jwt, Claims::getExpiration)
    }

    fun extractUsername(jwt: String): String? {
        return extractClaim(jwt, Claims::getSubject)
    }

    fun <T> extractClaim(jwt: String, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = extractAllClaims(jwt)
        return claimsResolver.apply(claims)
    }

    private fun extractAllClaims(jwt: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(jwt)
            .body
    }

    private fun getSigningKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
