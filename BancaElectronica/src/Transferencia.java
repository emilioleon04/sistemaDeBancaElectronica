import java.util.Date;

public class Transferencia {
    private String id;
    private Date fecha;
    private double monto;

    public Transferencia(String id, double monto) {
        this.id = id;
        this.fecha = new Date();
        this.monto = monto;
    }

    public String getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }
}
