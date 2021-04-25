package com.perales.spring.day.covid.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Municipio {
    private String id;
    private String nombre;
    private String clave;
}
