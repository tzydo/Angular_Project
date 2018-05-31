package com.pl.skijumping.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class DataRaceDTO {
    private Long id;
    private LocalDate date;
    private String city;
    private String shortCountryName;
    private String competitionName;
    private String competitionType;
    private Integer raceId;
}
