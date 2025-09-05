package org.rodry;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Estudiante {
    private String nombre;
    private int nota;
    private int edad;
}
