package com.techlab.models;

public class LineaPedido {
    private final Producto producto;
    private final int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto obtenerProducto() {
        return producto;
    }

    public int obtenerCantidad() {
        return cantidad;
    }

    public double calcularSubtotal() {
        return producto.obtenerPrecio() * cantidad;
    }
}