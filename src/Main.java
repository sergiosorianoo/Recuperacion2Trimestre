import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<Integer, Factura> facturas = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Opciones:");
            System.out.println("A. Crear Facturas (entrada: código y fecha)");
            System.out.println("B. Mostrar facturas (todas)");
            System.out.println("C. Añadir Producto a Factura (entrada: código de la factura, datos del producto y cantidad)");
            System.out.println("D. Eliminar Producto de la Factura (entrada: código de la factura y código del producto)");
            System.out.println("E. Mostrar total de una Factura (entrada: código de la factura)");
            System.out.println("F. Salir");

            String opcion = scanner.nextLine().toUpperCase();
            switch (opcion) {
                case "A":
                    crearFactura();
                    break;
                case "B":
                    mostrarFacturas();
                    break;
                case "C":
                    añadirProductoAFactura();
                    break;
                case "D":
                    eliminarProductoDeFactura();
                    break;
                case "E":
                    mostrarTotalFactura();
                    break;
                case "F":
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void crearFactura() {
        System.out.print("Ingrese el código de la factura: ");
        int codigoFactura = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la fecha de la factura: ");
        String fecha = scanner.nextLine();

        Factura factura = new Factura(codigoFactura, fecha);
        facturas.put(codigoFactura, factura);
        System.out.println("Factura creada exitosamente.");
    }

    private static void mostrarFacturas() {
        for (Factura factura : facturas.values()) {
            System.out.println("Código de factura: " + factura.getCodigoFactura());
            System.out.println("Fecha: " + factura.getFecha());
            for (LineaFactura lf : factura.getDatosFactura()) {
                System.out.println("Producto: " + lf.getProducto().getDescripcion() + ", Cantidad: " + lf.getCantidad() + ", Precio Total: " + lf.getTotal());
            }
            System.out.println("Total de la factura: " + factura.getTotalFactura());
            System.out.println();
        }
    }

    private static void añadirProductoAFactura() {
        System.out.print("Ingrese el código de la factura: ");
        int codigoFactura = Integer.parseInt(scanner.nextLine());
        Factura factura = facturas.get(codigoFactura);

        if (factura == null) {
            System.out.println("La factura no existe.");
            return;
        }

        System.out.print("Ingrese el código del producto: ");
        int codigoProducto = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la descripción del producto: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Ingrese el porcentaje de descuento (0 si no hay descuento): ");
        byte porcentajeDescuento = Byte.parseByte(scanner.nextLine());
        System.out.print("Ingrese la cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        Producto producto;
        if (porcentajeDescuento > 0) {
            producto = new ProductoOferta(codigoProducto, descripcion, precio, porcentajeDescuento);
        } else {
            producto = new Producto(codigoProducto, descripcion, precio);
        }

        LineaFactura linea = new LineaFactura(producto, cantidad);
        factura.agregarLineaFactura(linea);
        System.out.println("Producto añadido a la factura.");
    }

    private static void eliminarProductoDeFactura() {
        System.out.print("Ingrese el código de la factura: ");
        int codigoFactura = Integer.parseInt(scanner.nextLine());
        Factura factura = facturas.get(codigoFactura);

        if (factura == null) {
            System.out.println("La factura no existe.");
            return;
        }

        System.out.print("Ingrese el código del producto: ");
        int codigoProducto = Integer.parseInt(scanner.nextLine());
        factura.eliminarLineaFactura(codigoProducto);
        System.out.println("Producto eliminado de la factura.");
    }

    private static void mostrarTotalFactura() {
        System.out.print("Ingrese el código de la factura: ");
        int codigoFactura = Integer.parseInt(scanner.nextLine());
        Factura factura = facturas.get(codigoFactura);

        if (factura == null) {
            System.out.println("La factura no existe.");
            return;
        }

        System.out.println("Total de la factura: " + factura.getTotalFactura());
    }
}
