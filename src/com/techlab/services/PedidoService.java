package com.techlab.services;

import com.techlab.models.Pedido;
import com.techlab.models.LineaPedido;
import com.techlab.models.Producto;
import com.techlab.repositories.IPedidoRepository;
import com.techlab.exceptions.StockInsuficienteException;

import java.util.ArrayList;

public class PedidoService implements IPedidoService {
    private final IPedidoRepository repository;

    public PedidoService(IPedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pedido crearPedido(ArrayList<LineaPedido> lineas) throws StockInsuficienteException {
        boolean valido = true;
        int i = 0;

        while (i < lineas.size() && valido) {
            LineaPedido linea = lineas.get(i);
            Producto producto = linea.obtenerProducto();
            int cantidad = linea.obtenerCantidad();

            if (producto == null || cantidad <= 0 || cantidad > producto.obtenerStock()) {
                valido = false;
            }
            i++;
        }

        Pedido pedido = null;

        if (valido) {
            pedido = new Pedido();
            for (LineaPedido linea : lineas) {
                linea.obtenerProducto().disminuirStock(linea.obtenerCantidad());
                pedido.agregarLinea(linea);
            }
            repository.agregar(pedido);
        } else {
            throw new StockInsuficienteException("Stock insuficiente o datos inválidos.");
        }

        return pedido;
    }

    @Override
    public ArrayList<Pedido> listarPedidos() {
        return repository.listarTodos();
    }
}