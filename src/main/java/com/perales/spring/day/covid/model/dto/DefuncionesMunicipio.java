package com.perales.spring.day.covid.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DefuncionesMunicipio {
    private String nombre;
    private Integer cantidad;
    private String abreviacion;
}