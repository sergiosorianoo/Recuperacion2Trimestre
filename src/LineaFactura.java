public class LineaFactura {
    private Producto producto;
    private int cantidad;

    public LineaFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = (cantidad <= 0) ? 1 : cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad = cantidad;
        }
    }

    public double getTotal() {
        return producto.getPrecio() * cantidad;
    }
}
