package com.negocio.services;

import com.negocio.models.Producto;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class InventarioService {
    private List<Producto> productos;

    public InventarioService() {
        this.productos = new ArrayList<>();
        inicializarProductos();
    }

    private void inicializarProductos() {
        productos.add(new Producto(1, "Hamburguesa", 15.50, 20));
        productos.add(new Producto(2, "Pizza", 25.00, 15));
        productos.add(new Producto(3, "Tacos", 8.75, 30));
        productos.add(new Producto(4, "Refresco", 3.50, 50));
    }

    // ERROR 8: Bucle infinito potencial(check)
    public Producto buscarProductoPorId(int id) {
        int i = 0;
        while (i < productos.size()) { // Debería ser < en lugar de <= porque exede los limites
            if (productos.get(i).getId() == id) {
                return productos.get(i);
            }
            i++;
        }
        return null;
    }

    // ERROR 9: No actualiza el stock después de la venta (check)
    public boolean venderProducto(int id, int cantidad) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null && producto.hayStock(cantidad)) {
            producto.reducirStock(cantidad);   // No reduce el stock - ERROR LÓGICO (check)
            System.out.println("Venta realizada: " + producto.getNombre());
            return true;
        }
        return false;
    }

    // ERROR 10: Código duplicado y condición mal formulada
    public List<Producto> obtenerProductosDisponibles() {
        List<Producto> disponibles = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getStock() > 0) { // Debería ser > 0 (check)
                disponibles.add(producto);
            }
        }
        return disponibles;
    }

    public void  ResumenInventario(){
        System.out.println("inventario");
        for (Producto producto : productos){
            System.out.println("ID :"+ producto.getId() +
                    "nombre: " + producto.getNombre()+
                    "precio Q"+ producto.getPrecio()+
                    "stock: "+ producto.getStock());
        }

    }
    public boolean eliminarProductoPorId(int id, Scanner scanner) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                System.out.println("¿Estás seguro que deseas eliminar el producto '" + producto.getNombre() + "'? (s/n)");
                String confirmacion = scanner.next().toLowerCase();

                if (confirmacion.toUpperCase().equals("S")) {
                    productos.remove(producto);
                    System.out.println("Producto eliminado: " + producto.getNombre());
                    return true;
                } else {
                    System.out.println("Eliminación cancelada.");
                    return false;
                }
            }
        }
        System.out.println("No se encontró un producto con ID: " + id);
        return false;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productos;
    }
}
