package org.rodry;

import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       ejercicio02();
    }

    public static void ejercicio01(){
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        agregarEstudiantes(listaEstudiantes);

        //Filtrar estudiantes que aprobaron (nota >= 6)
        System.out.println("Estudiantes aprobados");
        List<Estudiante> estudiantesAprobados =
                listaEstudiantes.stream()
                        .filter(estudiante -> estudiante.getNota() >= 6)
                        .collect(Collectors.toList());
        estudiantesAprobados.forEach(estudiante -> {
            System.out.println("Nombre: " + estudiante.getNombre() + " Nota: " + estudiante.getNota());
        });
        //Ordenar aprobados de mayor a menor nota
        System.out.println("\n Aprobados Ordenados");
        List<Estudiante> aprobadosOrdenados =
                estudiantesAprobados.stream().sorted((a, b) -> b.getNota() - a.getNota())
                        .collect(Collectors.toList());
        aprobadosOrdenados.forEach(estudiante -> {
            System.out.println("Nombre: " + estudiante.getNombre() + " Nota: " + estudiante.getNota());
        });

        //Obtener el promedio de todos los estudiantes
        Double promedio =
                listaEstudiantes.stream().
                        collect(Collectors.averagingInt(Estudiante::getNota));
        System.out.println("\n El promedio de notas es de " + promedio);

        //Estudiante con mejor nota
        Optional<Estudiante> mejorEstudiante = listaEstudiantes.stream()
                .max(Comparator.comparingInt(Estudiante::getNota));

        mejorEstudiante.ifPresent(estudiante -> {
            System.out.println("\n El alumno con mejor nota es " + mejorEstudiante.get());
        });

        //Agrupar estudiantes por edad
        System.out.println("\n Estudiantes agrupados:");
        Map<Integer, List<Estudiante>> estudiantesPorEdad = listaEstudiantes.stream()
                .collect(Collectors.groupingBy(Estudiante::getEdad));

        estudiantesPorEdad.forEach((edad, estudiantes) -> {
            System.out.println("\nEstudiantes por edad: " + edad);
            estudiantes.forEach(estudiante -> {
                System.out.println(estudiante);
            });
        });

        //Convertir nombre a mayusculas
        System.out.println("\n Estudiantes con nombres en mayuscula");
        List<Estudiante> listaMayuscula = listaEstudiantes.stream()
                .map( e -> new Estudiante(
                        e.getNombre().toUpperCase(),
                        e.getNota(),
                        e.getEdad()
                ))
                .collect(Collectors.toList());

        listaMayuscula.forEach(estudiante -> {
            System.out.println(estudiante);
        });

    }

    public static void agregarEstudiantes(List<Estudiante> listaEstudiantes) {
        listaEstudiantes.add(new Estudiante("Rodrigo Bustos", 8, 33));
        listaEstudiantes.add(new Estudiante("Juan Garcia", 7, 33));
        listaEstudiantes.add(new Estudiante("Lucía Fernández", 9, 22));
        listaEstudiantes.add(new Estudiante("Martín Gómez", 5, 25));
        listaEstudiantes.add(new Estudiante("Camila López", 7, 20));
        listaEstudiantes.add(new Estudiante("Julián Pérez", 10, 24));
        listaEstudiantes.add(new Estudiante("Valentina Díaz", 4, 21));
        listaEstudiantes.add(new Estudiante("Sofía Martínez", 6, 23));
        listaEstudiantes.add(new Estudiante("Diego Torres", 8, 26));
        listaEstudiantes.add(new Estudiante("Micaela Sánchez", 3, 22));
        listaEstudiantes.add(new Estudiante("Facundo Herrera", 7, 27));
        listaEstudiantes.add(new Estudiante("Isabella Romero", 9, 19));
        listaEstudiantes.add(new Estudiante("Bruno Castillo", 5, 28));
        listaEstudiantes.add(new Estudiante("Agustina Morales", 6, 21));
        listaEstudiantes.add(new Estudiante("Tomás Varela", 10, 23));
        listaEstudiantes.add(new Estudiante("Camilo Rojas", 4, 24));
        listaEstudiantes.add(new Estudiante("Hernán Alvarez", 2, 22));
        listaEstudiantes.add(new Estudiante("Paula Méndez", 5, 24));
        listaEstudiantes.add(new Estudiante("Federico Cabrera", 3, 21));
        listaEstudiantes.add(new Estudiante("Camila Paredes", 2, 20));
    }

    public static void ejercicio02(){
        List<Producto> listaProductos = new ArrayList<>();
        agregarProductos(listaProductos);

        //Filtrar productos disponibles (stock > 0)
        System.out.println("\n Productos disponibles:");
        List<Producto> productosDisponibles = listaProductos.stream()
                .filter(p -> p.getStock() > 0)
                .collect(Collectors.toList());
        productosDisponibles.forEach(producto -> {
            System.out.println(producto);
        });

        //Calcular precio total del inventario
        double precioTotal = listaProductos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getStock())
                .sum();
        System.out.println("\n El precio total del inventario es: $" + precioTotal);

        //Obtener lista con ,os 3 productos mas caros
        System.out.println("\n Los Productos mas caros son:");
        List<Producto> masCaros = listaProductos.stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .limit(3)
                .collect(Collectors.toList());
        masCaros.forEach(producto -> {
            System.out.println(producto.getNombre() + " $" + producto.getPrecio());
        });

        //Buscar si existe algun producto de la categoria Electronica
        Optional<Producto> productoElectronica =
                listaProductos.stream()
                        .filter(p -> p.getCategoria().equals("Electrónica"))
                        .findAny();
        if (productoElectronica.isPresent()) {
            System.out.println("\nSi existen productos en la categoria Electronica");
        } else {
            System.out.println("\nNo existen productos en la categoria Electronica");
        }

        //Agrupar productos por categoria
        System.out.println("\nProductos agrupados por categoria");
        Map<String, List<Producto>> productosCategoria =
                listaProductos.stream()
                        .collect(Collectors.groupingBy(Producto::getCategoria));

        productosCategoria.forEach((categoria, producto) -> {
            System.out.println(categoria);
            producto.forEach(p -> {
                System.out.println(p);
            });
        });

        //Generar lista de nombres de productos ordenados alfabeticamente
        System.out.println("\nLista de nombres ordenados:");
        List<String> nombresProductos =
                listaProductos.stream()
                        .sorted(Comparator.comparing(Producto::getNombre))
                        .map(Producto::getNombre)
                        .collect(Collectors.toList());

        nombresProductos.forEach(System.out::println);
    }

    public static void agregarProductos(List<Producto> listaProductos) {
        listaProductos.add(new Producto("Smartphone Galaxy S21", "Electrónica", 799.99, 15));
        listaProductos.add(new Producto("Laptop Lenovo IdeaPad", "Electrónica", 950.00, 10));
        listaProductos.add(new Producto("Auriculares Sony WH-1000XM4", "Electrónica", 350.50, 20));
        listaProductos.add(new Producto("Televisor LG 55\"", "Electrónica", 1200.00, 5));
        listaProductos.add(new Producto("Cafetera Nespresso", "Hogar", 180.00, 25));
        listaProductos.add(new Producto("Silla ergonómica", "Hogar", 250.00, 12));
        listaProductos.add(new Producto("Mesa de comedor", "Hogar", 500.00, 0));
        listaProductos.add(new Producto("Lámpara de escritorio", "Hogar", 45.90, 40));
        listaProductos.add(new Producto("Remera básica", "Ropa", 15.99, 100));
        listaProductos.add(new Producto("Jeans Levi’s", "Ropa", 85.00, 35));
        listaProductos.add(new Producto("Campera de abrigo", "Ropa", 120.00, 18));
        listaProductos.add(new Producto("Zapatillas Nike Air", "Ropa", 150.00, 25));
        listaProductos.add(new Producto("Pack de arroz 1kg", "Alimentos", 2.50, 200));
        listaProductos.add(new Producto("Aceite de oliva 1L", "Alimentos", 6.80, 120));
        listaProductos.add(new Producto("Cereal Kellogg’s", "Alimentos", 4.75, 90));
        listaProductos.add(new Producto("Caja de galletitas", "Alimentos", 3.20, 150));
        listaProductos.add(new Producto("Pelota de fútbol Adidas", "Deportes", 35.00, 50));
        listaProductos.add(new Producto("Bicicleta Mountain Bike", "Deportes", 450.00, 7));
        listaProductos.add(new Producto("Mancuernas 10kg", "Deportes", 60.00, 30));
        listaProductos.add(new Producto("Colchoneta de yoga", "Deportes", 25.00, 45));
        listaProductos.add(new Producto("Mouse Logitech", "Electrónica", 35.00, 60));
        listaProductos.add(new Producto("Teclado mecánico", "Electrónica", 70.00, 40));
        listaProductos.add(new Producto("Impresora HP DeskJet", "Electrónica", 120.00, 0));
        listaProductos.add(new Producto("Aspiradora Philips", "Hogar", 200.00, 22));
        listaProductos.add(new Producto("Heladera Samsung", "Hogar", 1350.00, 0));
        listaProductos.add(new Producto("Microondas BGH", "Hogar", 160.00, 18));
        listaProductos.add(new Producto("Camisa formal", "Ropa", 55.00, 28));
        listaProductos.add(new Producto("Sweater de lana", "Ropa", 65.00, 20));
        listaProductos.add(new Producto("Pan integral", "Alimentos", 1.80, 140));
        listaProductos.add(new Producto("Botella de agua 2L", "Alimentos", 1.20, 300));

    }
}