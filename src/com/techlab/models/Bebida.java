package com.techlab.models;

public class Bebida extends Producto {
    private final double litros;

    public Bebida(String nombre, double precio, int stock, double litros) {
        super(nombre, precio, stock);
        this.litros = litros;
    }

    @Override
    public String descripcionDetallada() {
        return "Bebida - " + obtenerNombre() + ", Litros: " + litros;
    }
}
