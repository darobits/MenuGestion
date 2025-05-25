package com.techlab.products;

import java.io.Serializable;

public class Bebida extends Producto {
    private double volumenLitros;

    public Bebida(String nombre, double precio, int stock, double volumenLitros) {
        super(nombre, precio, stock);
        this.volumenLitros = volumenLitros;
    }

    @Override
    public String mostrarDetalles() {
        return getNombre() + " | Litros: " + volumenLitros;
    }
}
