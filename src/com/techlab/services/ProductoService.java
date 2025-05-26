package com.techlab.services;

import com.techlab.models.Producto;
import com.techlab.repositories.IProductoRepository;

import java.util.ArrayList;

public class ProductoService implements IProductoService {
    private final IProductoRepository repository;

    public ProductoService(IProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void agregarProducto(Producto producto) {
        repository.agregar(producto);
    }

    @Override
    public ArrayList<Producto> obtenerTodos() {
        return repository.listarTodos();
    }

    @Override
    public Producto buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    @Override
    public Producto buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }

    @Override
    public boolean actualizarProducto(int id, double nuevoPrecio, int nuevoStock) {
        Producto producto = buscarPorId(id);
        boolean actualizado = false;
        if (producto != null && nuevoPrecio > 0 && nuevoStock >= 0) {
            producto.actualizarPrecio(nuevoPrecio);
            producto.modificarStock(nuevoStock);
            actualizado = true;
        }
        return actualizado;
    }

    @Override
    public boolean eliminarProducto(int id) {
        Producto producto = buscarPorId(id);
        boolean eliminado = false;
        if (producto != null) {
            repository.eliminar(id);
            eliminado = true;
        }
        return eliminado;
    }
}