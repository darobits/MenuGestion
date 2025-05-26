package com.techlab.services;

import com.techlab.models.Pedido;
import com.techlab.models.Producto;
import com.techlab.models.LineaPedido;
import com.techlab.exceptions.StockInsuficienteException;

import java.util.ArrayList;

public interface IPedidoService {
    Pedido crearPedido(ArrayList<LineaPedido> lineas) throws StockInsuficienteException;
    ArrayList<Pedido> listarPedidos();
}