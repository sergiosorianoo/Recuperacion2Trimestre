import java.util.ArrayList;
import java.util.List;

public class Factura {
    private String fecha;
    private int codigoFactura;
    private List<LineaFactura> lineasFactura;

    public Factura(int codigoFactura, String fecha) {
        this.codigoFactura = codigoFactura;
        this.fecha = fecha;
        this.lineasFactura = new ArrayList<>();
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void agregarLineaFactura(LineaFactura linea) {
        for (LineaFactura lf : lineasFactura) {
            if (lf.getProducto().getCodigo() == linea.getProducto().getCodigo()) {
                System.out.println("El producto ya está en la factura.");
                return;
            }
        }
        lineasFactura.add(linea);
    }

    public void eliminarLineaFactura(int codigoProducto) {
        lineasFactura.removeIf(lf -> lf.getProducto().getCodigo() == codigoProducto);
    }

    public LineaFactura buscarLineaFactura(int codigoProducto) {
        for (LineaFactura lf : lineasFactura) {
            if (lf.getProducto().getCodigo() == codigoProducto) {
                return lf;
            }
        }
        return null;
    }

    public LineaFactura[] getDatosFactura() {
        return lineasFactura.toArray(new LineaFactura[0]);
    }

    public double getTotalFactura() {
        double total = 0;
        for (LineaFactura lf : lineasFactura) {
            total += lf.getTotal();
        }
        return total;
    }
}
