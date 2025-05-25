package com.techlab;

import com.techlab.exceptions.StockInsuficienteException;
import com.techlab.models.*;
import com.techlab.repositories.ProductoRepository;
import com.techlab.repositories.PedidoRepository;
import com.techlab.services.ProductoService;
import com.techlab.services.PedidoService;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductoService productoService = new ProductoService(new ProductoRepository());
    private static final PedidoService pedidoService = new PedidoService(new PedidoRepository());
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Seleccione una opción: ");

            boolean opcionValida = true;

            if (opcion == 1) {
                gestionarProductos();
            } else if (opcion == 2) {
                gestionarPedidos();
            } else if (opcion == 3) {
                listarTodo();
            } else if (opcion == 4) {
                salir = true;
                System.out.println("\nSaliendo del sistema...");
            } else {
                opcionValida = false;
                System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Productos");
        System.out.println("2. Gestión de Pedidos");
        System.out.println("3. Listar Todo");
        System.out.println("4. Salir");
    }

    private static void gestionarProductos() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Listar Productos");
            System.out.println("5. Volver al Menú Principal");

            int opcion = leerEntero("Seleccione una opción: ");
            boolean opcionValida = true;

            if (opcion == 1) {
                agregarProducto();
            } else if (opcion == 2) {
                modificarProducto();
            } else if (opcion == 3) {
                eliminarProducto();
            } else if (opcion == 4) {
                listarProductos();
            } else if (opcion == 5) {
                volver = true;
            } else {
                opcionValida = false;
                System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        }
    }

    private static void agregarProducto() {
        System.out.println("\n=== AGREGAR PRODUCTO ===");
        System.out.println("1. Bebida");
        System.out.println("2. Comida");
        System.out.println("3. Ingrediente/Producto sin vencimiento");
        System.out.println("4. Cancelar");

        int tipo = leerEntero("Seleccione el tipo de producto: ");
        boolean cancelar = tipo < 1 || tipo > 3;

        if (!cancelar) {
            String nombre = leerString("Nombre del producto: ");
            double precio = leerDouble("Precio: ");
            int stock = leerEntero("Stock inicial: ");

            Producto producto = null;
            boolean error = false;

            try {
                if (tipo == 1) {
                    double litros = leerDouble("Volumen en litros: ");
                    producto = new Bebida(nombre, precio, stock, litros);
                } else if (tipo == 2) {
                    String fechaVencimiento = leerString("Fecha de vencimiento (dd/mm/aaaa): ");
                    producto = new Comida(nombre, precio, stock, fechaVencimiento);
                } else if (tipo == 3) {
                    producto = new Comida(nombre, precio, stock, "N/A");
                }

                if (producto != null) {
                    productoService.agregarProducto(producto);
                    System.out.println("\nProducto agregado exitosamente!");
                    System.out.println(producto.descripcionDetallada());
                }
            } catch (Exception e) {
                error = true;
                System.out.println("\nError al agregar producto: " + e.getMessage());
            }
        } else {
            System.out.println("\nOperación cancelada.");
        }
    }

    private static void modificarProducto() {
        System.out.println("\n=== MODIFICAR PRODUCTO ===");
        listarProductos();

        int id = leerEntero("\nIngrese ID del producto a modificar (0 para cancelar): ");
        boolean cancelar = id == 0;

        if (!cancelar) {
            Producto producto = productoService.buscarPorId(id);
            boolean productoEncontrado = producto != null;

            if (productoEncontrado) {
                System.out.println("\nProducto seleccionado:");
                System.out.println(producto.descripcionDetallada());

                double nuevoPrecio = leerDouble("Nuevo precio (actual: " + producto.obtenerPrecio() + "): ");
                int nuevoStock = leerEntero("Nuevo stock (actual: " + producto.obtenerStock() + "): ");

                if (productoService.actualizarProducto(id, nuevoPrecio, nuevoStock)) {
                    System.out.println("\nProducto actualizado exitosamente!");
                } else {
                    System.out.println("\nError al actualizar producto. Verifique los datos.");
                }
            } else {
                System.out.println("\nProducto no encontrado.");
            }
        }
    }

    private static void eliminarProducto() {
        System.out.println("\n=== ELIMINAR PRODUCTO ===");
        listarProductos();

        int id = leerEntero("\nIngrese ID del producto a eliminar (0 para cancelar): ");
        boolean cancelar = id == 0;

        if (!cancelar) {
            if (productoService.eliminarProducto(id)) {
                System.out.println("\nProducto eliminado exitosamente!");
            } else {
                System.out.println("\nError al eliminar producto. ID no encontrado.");
            }
        }
    }

    private static void listarProductos() {
        System.out.println("\n=== LISTADO DE PRODUCTOS ===");
        ArrayList<Producto> productos = productoService.obtenerTodos();
        boolean hayProductos = !productos.isEmpty();

        if (hayProductos) {
            for (Producto p : productos) {
                System.out.println("ID: " + p.obtenerId() + " | " + p.descripcionDetallada() +
                        " | Precio: $" + df.format(p.obtenerPrecio()) +
                        " | Stock: " + p.obtenerStock());
            }
        } else {
            System.out.println("No hay productos registrados.");
        }
    }

    private static void gestionarPedidos() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n=== GESTIÓN DE PEDIDOS ===");
            System.out.println("1. Crear Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Volver al Menú Principal");

            int opcion = leerEntero("Seleccione una opción: ");
            boolean opcionValida = true;

            if (opcion == 1) {
                crearPedido();
            } else if (opcion == 2) {
                listarPedidos();
            } else if (opcion == 3) {
                volver = true;
            } else {
                opcionValida = false;
                System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        }
    }

    private static void crearPedido() {
        System.out.println("\n=== CREAR PEDIDO ===");
        ArrayList<LineaPedido> lineas = new ArrayList<>();
        boolean agregarMas = true;
        boolean pedidoCancelado = false;

        while (agregarMas && !pedidoCancelado) {
            listarProductos();

            int idProducto = leerEntero("\nIngrese ID del producto (0 para finalizar): ");
            boolean finalizar = idProducto == 0;

            if (!finalizar) {
                Producto producto = productoService.buscarPorId(idProducto);
                boolean productoEncontrado = producto != null;

                if (productoEncontrado) {
                    System.out.println("\nProducto seleccionado: " + producto.descripcionDetallada());
                    System.out.println("Stock disponible: " + producto.obtenerStock());

                    int cantidad = leerEntero("Cantidad: ");
                    boolean cantidadValida = cantidad > 0 && cantidad <= producto.obtenerStock();

                    if (cantidadValida) {
                        lineas.add(new LineaPedido(producto, cantidad));
                        System.out.println("\nProducto agregado al pedido.");

                        agregarMas = leerSiNo("¿Desea agregar otro producto? (s/n): ");
                    } else {
                        System.out.println("\nCantidad inválida o stock insuficiente.");
                    }
                } else {
                    System.out.println("\nProducto no encontrado. Intente nuevamente.");
                }
            } else {
                agregarMas = false;
                pedidoCancelado = lineas.isEmpty();
            }
        }

        if (!pedidoCancelado) {
            try {
                Pedido pedido = pedidoService.crearPedido(lineas);
                System.out.println("\nPedido creado exitosamente! ID: " + pedido.obtenerId());
                System.out.println("Total: $" + df.format(pedido.calcularTotal()));
            } catch (StockInsuficienteException e) {
                System.out.println("\nError al crear pedido: " + e.getMessage());
            }
        } else {
            System.out.println("\nPedido cancelado. No se agregaron productos.");
        }
    }

    private static void listarPedidos() {
        System.out.println("\n=== LISTADO DE PEDIDOS ===");
        ArrayList<Pedido> pedidos = pedidoService.listarPedidos();
        boolean hayPedidos = !pedidos.isEmpty();

        if (hayPedidos) {
            for (Pedido pedido : pedidos) {
                System.out.println("\nPedido ID: " + pedido.obtenerId());
                System.out.println("---------------------------------");

                for (LineaPedido linea : pedido.obtenerLineas()) {
                    Producto p = linea.obtenerProducto();
                    System.out.println(p.obtenerNombre() +
                            " x" + linea.obtenerCantidad() +
                            " | $" + df.format(p.obtenerPrecio()) +
                            " c/u | Subtotal: $" + df.format(linea.calcularSubtotal()));
                }

                System.out.println("---------------------------------");
                System.out.println("TOTAL: $" + df.format(pedido.calcularTotal()));
            }
        } else {
            System.out.println("No hay pedidos registrados.");
        }
    }

    private static void listarTodo() {
        listarProductos();
        listarPedidos();
    }

    // Métodos auxiliares para entrada de datos
    private static int leerEntero(String mensaje) {
        boolean valido = false;
        int resultado = 0;

        while (!valido) {
            try {
                System.out.print(mensaje);
                resultado = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número entero.");
            }
        }

        return resultado;
    }

    private static double leerDouble(String mensaje) {
        boolean valido = false;
        double resultado = 0;

        while (!valido) {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine().replace(',', '.');
                resultado = Double.parseDouble(input);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número (use punto o coma para decimales).");
            }
        }

        return resultado;
    }

    private static String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static boolean leerSiNo(String mensaje) {
        boolean valido = false;
        boolean resultado = false;

        while (!valido) {
            System.out.print(mensaje);
            String respuesta = scanner.nextLine().toLowerCase();

            if (respuesta.equals("s")) {
                resultado = true;
                valido = true;
            } else if (respuesta.equals("n")) {
                resultado = false;
                valido = true;
            } else {
                System.out.println("Por favor ingrese 's' para sí o 'n' para no.");
            }
        }

        return resultado;
    }
}
