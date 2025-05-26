package com.techlab.repositories;

import com.techlab.models.Producto;
import java.util.ArrayList;

public interface IProductoRepository {
    void agregar(Producto producto);
    Producto buscarPorId(int id);
    Producto buscarPorNombre(String nombre);
    void eliminar(int id);
    ArrayList<Producto> listarTodos();
}