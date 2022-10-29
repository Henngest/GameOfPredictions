package com.henngest.gameofpredictions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users",
       uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
  @NonNull private String username;
  @NonNull private String password;
}
