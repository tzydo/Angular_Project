package com.pl.skijumping.domain.entity;

import com.pl.skijumping.domain.model.Gender;
import com.pl.skijumping.domain.model.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ski_jumper")
@NoArgsConstructor
@AllArgsConstructor
public class SkiJumper implements Serializable {

    private Long id;
    private int fisCode;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String nationality;
    private String team;
    private List<JumpResultToSkiJumper> jumpResultToSkiJumpers;
    private MaritalStatus martialStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "fis_code")
    public int getFisCode() {
        return fisCode;
    }

    public void setFisCode(int fisCode) {
        this.fisCode = fisCode;
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birthday")
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Column(name = "team")
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @OneToMany(mappedBy = "skiJumper")
    public List<JumpResultToSkiJumper> getJumpResultToSkiJumpers() {
        return jumpResultToSkiJumpers;
    }

    public void setJumpResultToSkiJumpers(List<JumpResultToSkiJumper> jumpResultToSkiJumpers) {
        this.jumpResultToSkiJumpers = jumpResultToSkiJumpers;
    }

    @Column(name = "martial_status")
    @Enumerated(EnumType.STRING)
    public MaritalStatus getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(MaritalStatus martialStatus) {
        this.martialStatus = martialStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkiJumper skiJumper = (SkiJumper) o;

        if (fisCode != skiJumper.fisCode) return false;
        if (id != null ? !id.equals(skiJumper.id) : skiJumper.id != null) return false;
        if (name != null ? !name.equals(skiJumper.name) : skiJumper.name != null) return false;
        if (birthday != null ? !birthday.equals(skiJumper.birthday) : skiJumper.birthday != null) return false;
        if (gender != skiJumper.gender) return false;
        if (nationality != null ? !nationality.equals(skiJumper.nationality) : skiJumper.nationality != null)
            return false;
        if (team != null ? !team.equals(skiJumper.team) : skiJumper.team != null) return false;
        if (jumpResultToSkiJumpers != null ? !jumpResultToSkiJumpers.equals(skiJumper.jumpResultToSkiJumpers) : skiJumper.jumpResultToSkiJumpers != null)
            return false;
        return martialStatus == skiJumper.martialStatus;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + fisCode;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (jumpResultToSkiJumpers != null ? jumpResultToSkiJumpers.hashCode() : 0);
        result = 31 * result + (martialStatus != null ? martialStatus.hashCode() : 0);
        return result;
    }

    public SkiJumper id(Long id) {
        this.id = id;
        return this;
    }

    public SkiJumper fisCode(int fisCode) {
        this.fisCode = fisCode;
        return this;
    }

    public SkiJumper name(String name) {
        this.name = name;
        return this;
    }

    public SkiJumper birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public SkiJumper gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public SkiJumper nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public SkiJumper team(String team) {
        this.team = team;
        return this;
    }

    public SkiJumper martialStatus(MaritalStatus martialStatus) {
        this.martialStatus = martialStatus;
        return this;
    }

    public SkiJumper jumpResultToSkiJumpers(List<JumpResultToSkiJumper> jumpResultToSkiJumpers) {
        this.jumpResultToSkiJumpers = jumpResultToSkiJumpers;
        return this;
    }
}