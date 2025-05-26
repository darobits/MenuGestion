package com.techlab.repositories;

import com.techlab.models.Pedido;
import java.util.ArrayList;

public class PedidoRepository implements IPedidoRepository {
    private final ArrayList<Pedido> pedidos = new ArrayList<>();

    @Override
    public void agregar(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public ArrayList<Pedido> listarTodos() {
        return pedidos;
    }
}