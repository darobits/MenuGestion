package com.techlab.models;

public class Comida extends Producto {
    private final String fechaVencimiento;

    public Comida(String nombre, double precio, int stock, String fechaVencimiento) {
        super(nombre, precio, stock);
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String descripcionDetallada() {
        return "Comida - " + obtenerNombre() + ", Vence: " + fechaVencimiento;
    }
}