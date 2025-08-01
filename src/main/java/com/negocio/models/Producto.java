package com.negocio.models;

// ERROR 1: Atributos públicos (Mala práctica de encapsulamiento)
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    private int cantidad; //para resolver problema 5


    // ERROR 2: Constructor sin validaciones (check)
    public Producto(int id, String nombre, double precio, int stock) {
        if (id < 0) throw new IllegalArgumentException("El ID no puede ser negativo.");
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock; // No valida si el stock es negativo (check)
        this.cantidad = 0;
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getCantidad() {
        return cantidad;
    }
    // Setters
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("El ID no puede ser negativo.");
        this.id = id;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
        this.stock = stock;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        this.cantidad = cantidad;
    }

    // ERROR 3: Método que permite stock negativo (check)
    public void reducirStock(int cantidad) {
        if (stock < cantidad) System.out.println("no contamos con esa cantidad solo con" + stock);
        if (cantidad <= 0) System.out.println("la cantidad no puede ser negativa");

        this.stock = this.stock - cantidad; // No verifica si hay suficiente stock
    }

    // ERROR 4: Método con lógica incorrecta (check)
    public boolean hayStock(int cantidad) {
        return stock >= cantidad; // Debería ser >= para permitir exactamente la cantidad (check)
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
