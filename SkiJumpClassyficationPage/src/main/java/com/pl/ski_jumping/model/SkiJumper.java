package com.pl.ski_jumping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkiJumper {
    private int rank;
    private int bib;
    private int fis_code;
    private String name;
    private String lastName;
    private String nationality;
    private double first_jump;
    private double points_for_first_jump;
    private double second_jump;
    private double points_for_second_jump;
    private double total_points;
}