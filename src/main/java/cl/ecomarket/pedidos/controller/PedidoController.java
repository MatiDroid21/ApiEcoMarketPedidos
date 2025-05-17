package cl.ecomarket.pedidos.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedidos.DTO.PedidoDTO;
import cl.ecomarket.pedidos.model.DetallePedido;
import cl.ecomarket.pedidos.model.Pedido;
import cl.ecomarket.pedidos.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
@CrossOrigin
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(pedidoDTO.getClienteId());
        pedido.setTiendaId(pedidoDTO.getTiendaId());
        pedido.setEmpleadoId(pedidoDTO.getEmpleadoId());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setMetodoPago(pedidoDTO.getMetodoPago());
        pedido.setSubtotal(pedidoDTO.getSubtotal());
        pedido.setDescuento(pedidoDTO.getDescuento());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setFechaPedido(LocalDateTime.now());

        List<DetallePedido> detalles = pedidoDTO.getDetalles().stream().map(detalleDTO -> {
            DetallePedido detalle = new DetallePedido();
            detalle.setProductoId(detalleDTO.getProductoId());
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
            detalle.setPedido(pedido); // pa’ que se asocie al pedido
            return detalle;
        }).collect(Collectors.toList());

        pedido.setDetalles(detalles);

        Pedido pedidoGuardado = pedidoService.guardarPedido(pedido);
        return ResponseEntity.ok(pedidoGuardado);
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarPedido(id, pedido);
            if (pedidoActualizado == null) {
                // Si no encontró el pedido para actualizar
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(pedidoActualizado);
        } catch (Exception e) {
            // Manejo general de errores, podés personalizarlo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
    }
}