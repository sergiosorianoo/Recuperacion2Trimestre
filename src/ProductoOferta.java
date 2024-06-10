public class ProductoOferta extends Producto {
    private byte porcentajeDescuento;

    public ProductoOferta(int codigo, String descripcion, double precio, byte porcentajeDescuento) {
        super(codigo, descripcion, precio);
        this.porcentajeDescuento = (porcentajeDescuento < 1 || porcentajeDescuento > 50) ? 1 : porcentajeDescuento;
    }

    public byte getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(byte porcentajeDescuento) {
        if (porcentajeDescuento >= 1 && porcentajeDescuento <= 50) {
            this.porcentajeDescuento = porcentajeDescuento;
        }
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() * (1 - (porcentajeDescuento / 100.0));
    }
}
