package com.techlab.repositories;

import com.techlab.models.Pedido;
import java.util.ArrayList;

public interface IPedidoRepository {
    void agregar(Pedido pedido);
    ArrayList<Pedido> listarTodos();
}