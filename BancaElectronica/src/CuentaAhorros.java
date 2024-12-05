public class CuentaAhorros extends Cuenta {
    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, double saldoInicial, double tasaInteres) {
        super(numeroCuenta, saldoInicial);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarInteres() {
        double interes = getSaldo() * tasaInteres / 100;
        depositar(interes);
    }

    public double getTasaInteres() {
        return tasaInteres;
    }
}
