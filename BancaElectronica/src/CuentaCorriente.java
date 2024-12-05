public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, double saldoInicial, double limiteSobregiro) {
        super(numeroCuenta, saldoInicial);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto > 0 && (getSaldo() - monto >= -limiteSobregiro)) {
            depositar(-monto);
            return true;
        }
        return false;
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }
}
