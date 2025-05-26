package com.techlab.models;

import java.util.ArrayList;

public class Pedido {
    private static int contador = 0;
    private final int id;
    private final ArrayList<LineaPedido> lineas;

    public Pedido() {
        this.id = ++contador;
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public int obtenerId() {
        return id;
    }

    public ArrayList<LineaPedido> obtenerLineas() {
        return lineas;
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.calcularSubtotal();
        }
        return total;
    }
}