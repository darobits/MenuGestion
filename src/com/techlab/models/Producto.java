package com.techlab.models;

public abstract class Producto {
    private static int contador = 0;
    private final int id;
    private final String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = ++contador;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int obtenerId() {
        return id;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public double obtenerPrecio() {
        return precio;
    }

    public int obtenerStock() {
        return stock;
    }

    public void actualizarPrecio(double nuevoPrecio) {
        if (nuevoPrecio > 0) {
            this.precio = nuevoPrecio;
        }
    }

    public void modificarStock(int cantidad) {
        if (cantidad >= 0) {
            this.stock = cantidad;
        }
    }

    public void disminuirStock(int cantidad) {
        if (cantidad <= stock) {
            this.stock -= cantidad;
        }
    }

    public abstract String descripcionDetallada();
}