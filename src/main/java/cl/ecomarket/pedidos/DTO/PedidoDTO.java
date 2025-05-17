package cl.ecomarket.pedidos.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

// PedidoDTO.java
@Data
public class PedidoDTO {
    private Long clienteId;
    private Long tiendaId;
    private Long empleadoId;
    private String estado;
    private String metodoPago;
    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal total;
    private List<DetalleDTO> detalles;

    @Data
    public static class DetalleDTO {
        private Long productoId;
        private int cantidad;
        private BigDecimal precioUnitario;
    }
}
