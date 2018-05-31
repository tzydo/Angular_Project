package com.pl.skijumping.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CountryDTO {
    private int id;
    private String name;
}
