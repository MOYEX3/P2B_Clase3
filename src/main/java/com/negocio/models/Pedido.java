package com.negocio.models;

import com.negocio.services.PedidoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Producto> productos;
    private LocalDateTime fecha;
    private double total;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.fecha = LocalDateTime.now();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        calcularTotal();
    }

    // ERROR 5: Cálculo incorrecto del total (suma precios sin considerar cantidades) (check)
    private void calcularTotal() {

        total = 0;
        for (Producto producto : productos) {
            total = producto.getPrecio() * producto.getCantidad() ; // Suma solo el precio, no considera cantidad(check)

        }

    }

    // ERROR 6: Método que puede causar IndexOutOfBoundsException (check)
    public Producto obtenerPrimerProducto() {
        if (productos==null || productos.isEmpty()){ //con esto le decimos que si la lista no ha sido creada (null) o no tiene productos (empty) mande el mensaje en consola
            System.out.println("La lista no puede estar vacia");
            return null;
        }
        return productos.get(0); // No verifica si la lista está vacía
    }

    // ERROR 7: Descuento mal aplicado (check)
    public double aplicarDescuento(double porcentaje) {
        // Aplica el descuento sumándolo en lugar de restándolo (check)
        return total - (total * porcentaje / 100);
    }

    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }
    public LocalDateTime getFecha() { return fecha; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNombre() +
                ", productos=" + productos.size() +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
