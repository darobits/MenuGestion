package com.techlab.repositories;

import com.techlab.models.Producto;
import java.util.ArrayList;

public class ProductoRepository implements IProductoRepository {
    private final ArrayList<Producto> productos = new ArrayList<>();

    @Override
    public void agregar(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto buscarPorId(int id) {
        int i = 0;
        Producto resultado = null;
        while (i < productos.size() && resultado == null) {
            Producto aux = productos.get(i);
            if (aux.obtenerId() == id) {
                resultado = aux;
            }
            i++;
        }
        return resultado;
    }

    @Override
    public Producto buscarPorNombre(String nombre) {
        int i = 0;
        Producto resultado = null;
        while (i < productos.size() && resultado == null) {
            Producto aux = productos.get(i);
            if (aux.obtenerNombre().equalsIgnoreCase(nombre)) {
                resultado = aux;
            }
            i++;
        }
        return resultado;
    }

    @Override
    public void eliminar(int id) {
        Producto producto = buscarPorId(id);
        if (producto != null) {
            productos.remove(producto);
        }
    }

    @Override
    public ArrayList<Producto> listarTodos() {
        return productos;
    }
}