package cl.ecomarket.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.pedidos.model.Pedido;
import cl.ecomarket.pedidos.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setClienteId(pedidoActualizado.getClienteId());
            pedido.setTiendaId(pedidoActualizado.getTiendaId());
            pedido.setEmpleadoId(pedidoActualizado.getEmpleadoId());
            pedido.setFechaPedido(pedidoActualizado.getFechaPedido());
            pedido.setEstado(pedidoActualizado.getEstado());
            pedido.setSubtotal(pedidoActualizado.getSubtotal());
            pedido.setDescuento(pedidoActualizado.getDescuento());
            pedido.setTotal(pedidoActualizado.getTotal());
            pedido.setMetodoPago(pedidoActualizado.getMetodoPago());
            return pedidoRepository.save(pedido);
        }).orElse(null);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
