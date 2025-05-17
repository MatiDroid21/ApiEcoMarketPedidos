package cl.ecomarket.pedidos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PEDIDOS")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PEDIDO_ID")
    private Long pedidoId;

    @Column(name = "CLIENTE_ID", nullable = false)
    private Long clienteId;

    @Column(name = "TIENDA_ID")
    private Long tiendaId;

    @Column(name = "EMPLEADO_ID")
    private Long empleadoId;

    @Column(name = "FECHA_PEDIDO")
    private LocalDateTime fechaPedido;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "SUBTOTAL", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "DESCUENTO")
    private BigDecimal descuento;

    @Column(name = "TOTAL", nullable = false)
    private BigDecimal total;

    @Column(name = "METODO_PAGO")
    private String metodoPago;
}