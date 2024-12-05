import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Bienvenido al Sistema de Banca Electrónica ===");
        boolean running = true;

        while (running) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Registro de Usuario");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("Gracias por usar el sistema. ¡Adiós!");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    private static void registrarUsuario() {
        System.out.print("\nIngrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        // Validar si el correo ya existe
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                System.out.println("Error: El correo ya está registrado.");
                return;
            }
        }

        // Registrar nuevo usuario
        usuarios.add(new Usuario(nombre, correo, contrasena));
        System.out.println("¡Registro exitoso! Puede iniciar sesión ahora.");
    }

    private static void iniciarSesion() {
        System.out.print("\nIngrese su correo: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo) &&
                usuario.getContrasena().equals(contrasena)) {
                System.out.println("¡Bienvenido, " + usuario.getNombre() + "!");
                menuCuentas(usuario);
                return;
            }
        }
        System.out.println("Error: Credenciales incorrectas.");
    }

    private static void menuCuentas(Usuario usuario) {
        boolean gestionandoCuentas = true;

        while (gestionandoCuentas) {
            System.out.println("\nGestión de Cuentas:");
            System.out.println("1. Crear Cuenta");
            System.out.println("2. Consultar Saldo");
            System.out.println("3. Depositar Dinero");
            System.out.println("4. Retirar Dinero");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearCuenta(usuario);
                    break;
                case 2:
                    consultarSaldo(usuario);
                    break;
                case 3:
                    depositarDinero(usuario);
                    break;
                case 4:
                    retirarDinero(usuario);
                    break;
                case 5:
                    gestionandoCuentas = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void crearCuenta(Usuario usuario) {
        System.out.print("\nIngrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingrese el saldo inicial: ");
        double saldoInicial = scanner.nextDouble();
        System.out.println("Seleccione el tipo de cuenta:");
        System.out.println("1. Cuenta Corriente");
        System.out.println("2. Cuenta de Ahorros");
        int tipoCuenta = scanner.nextInt();

        switch (tipoCuenta) {
            case 1:
                System.out.print("Ingrese el límite de sobregiro: ");
                double limiteSobregiro = scanner.nextDouble();
                usuario.agregarCuenta(new CuentaCorriente(numeroCuenta, saldoInicial, limiteSobregiro));
                System.out.println("Cuenta Corriente creada exitosamente.");
                break;
            case 2:
                System.out.print("Ingrese la tasa de interés (%): ");
                double tasaInteres = scanner.nextDouble();
                usuario.agregarCuenta(new CuentaAhorros(numeroCuenta, saldoInicial, tasaInteres));
                System.out.println("Cuenta de Ahorros creada exitosamente.");
                break;
            default:
                System.out.println("Tipo de cuenta no válido.");
        }
    }

    private static void consultarSaldo(Usuario usuario) {
        Cuenta cuenta = buscarCuenta(usuario);
        if (cuenta != null) {
            System.out.println("El saldo de la cuenta " + cuenta.getNumeroCuenta() + " es: " + cuenta.getSaldo());
        }
    }

    private static void depositarDinero(Usuario usuario) {
        Cuenta cuenta = buscarCuenta(usuario);
        if (cuenta != null) {
            System.out.print("Ingrese la cantidad a depositar: ");
            double monto = scanner.nextDouble();
            cuenta.depositar(monto);
            System.out.println("Depósito realizado con éxito. Nuevo saldo: " + cuenta.getSaldo());
        }
    }

    private static void retirarDinero(Usuario usuario) {
        Cuenta cuenta = buscarCuenta(usuario);
        if (cuenta != null) {
            System.out.print("Ingrese la cantidad a retirar: ");
            double monto = scanner.nextDouble();
            if (cuenta.retirar(monto)) {
                System.out.println("Retiro realizado con éxito. Nuevo saldo: " + cuenta.getSaldo());
            } else {
                System.out.println("Fondos insuficientes o monto no válido.");
            }
        }
    }

    private static Cuenta buscarCuenta(Usuario usuario) {
        System.out.print("\nIngrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        for (Cuenta cuenta : usuario.getCuentas()) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        System.out.println("Cuenta no encontrada.");
        return null;
    }
}
