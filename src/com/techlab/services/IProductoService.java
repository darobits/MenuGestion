package com.techlab.services;

import com.techlab.models.Producto;
import java.util.ArrayList;

public interface IProductoService {
    void agregarProducto(Producto producto);
    ArrayList<Producto> obtenerTodos();
    Producto buscarPorId(int id);
    Producto buscarPorNombre(String nombre);
    boolean actualizarProducto(int id, double nuevoPrecio, int nuevoStock);
    boolean eliminarProducto(int id);
}