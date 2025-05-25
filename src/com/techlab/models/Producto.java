package com.techlab.products;

public abstract class Producto {
    private static int contador = 0;
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = ++contador;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }

    public abstract String mostrarDetalles();
}
