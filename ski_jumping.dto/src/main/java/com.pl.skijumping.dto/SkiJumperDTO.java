package com.pl.skijumping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkiJumperDTO implements Serializable {

    private Long id;
    private Integer fisCode;
    private String name;
    private LocalDate birthday;
    private String nationality;
    private String team;
    private String gender;
    private String martialStatus;

    public SkiJumperDTO id(Long id) {
        this.id = id;
        return this;
    }

    public SkiJumperDTO fisCode(Integer fisCode) {
        this.fisCode = fisCode;
        return this;
    }

    public SkiJumperDTO name(String name) {
        this.name = name;
        return this;
    }

    public SkiJumperDTO nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public SkiJumperDTO team(String team) {
        this.team = team;
        return this;
    }

    public SkiJumperDTO martialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
        return this;
    }

    public SkiJumperDTO birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public SkiJumperDTO gender(String gender) {
        this.gender = gender;
        return this;
    }
}
