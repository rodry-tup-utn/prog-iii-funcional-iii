package org.rodry;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
}
