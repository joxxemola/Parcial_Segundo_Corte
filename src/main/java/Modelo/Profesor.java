package modelo;

/**
 * Clase Profesor — hereda de Persona.
 *
 * FÓRMULA DE PAGO:
 *   pagoBase       = salarioPorHora × horasMes
 *   pagoTotal      = pagoBase × 1.30   (+ 30 % preparación)
 *   prestaciones   = pagoTotal × 0.19  (19 % a cargo del colegio)
 *
 * POLIMORFISMO DE SOBRECARGA:
 *   calcularPago()           — sin horas extra
 *   calcularPago(int extra)  — con horas extra adicionales
 *
 * POLIMORFISMO DE SOBREESCRITURA:
 *   mostrarInfo()  — implementación propia de Persona
 */
public class Profesor extends Persona {

    // ── Atributos ──────────────────────────────────────────────────────────
    private String cedula;
    private Area   area;           // Asociación con Area
    private double salarioPorHora;
    private int    horasMes;

    // ── Constructor (único — requerido en esta subclase) ───────────────────
    public Profesor(String nombre, String direccion,
                    String telefono, String fechaNacimiento,
                    String cedula, Area area,
                    double salarioPorHora, int horasMes) {
        super(nombre, direccion, telefono, fechaNacimiento);
        this.cedula          = cedula;
        this.area            = area;
        this.salarioPorHora  = salarioPorHora;
        this.horasMes        = horasMes;
    }

    // ── Métodos de negocio ────────────────────────────────────────────────

    /** SOBRECARGA 1 — pago sin horas extra */
    public double calcularPago() {
        double base = salarioPorHora * horasMes;
        return base * 1.30;
    }

    /** SOBRECARGA 2 — pago con horas extra adicionales */
    public double calcularPago(int horasExtra) {
        double base = salarioPorHora * (horasMes + horasExtra);
        return base * 1.30;
    }

    /** Prestaciones sociales = 19 % del pago mensual */
    public double calcularPrestaciones() {
        return calcularPago() * 0.19;
    }

    // ── Sobreescritura de mostrarInfo() ───────────────────────────────────
    @Override
    public String mostrarInfo() {
        return String.format(
            "PROFESOR  | %-20s | CC: %-12s | Área: %-15s | Pago: $%,.2f | Prestaciones: $%,.2f",
            getNombre(), cedula, area.getNombre(),
            calcularPago(), calcularPrestaciones()
        );
    }

    @Override
    public String toString() {
        return getNombre() + " — " + area.getNombre();
    }

    // ── Getters y Setters ─────────────────────────────────────────────────
    public String getCedula()           { return cedula; }
    public void   setCedula(String c)   { this.cedula = c; }

    public Area getArea()           { return area; }
    public void setArea(Area a)     { this.area = a; }

    public double getSalarioPorHora()              { return salarioPorHora; }
    public void   setSalarioPorHora(double s)      { this.salarioPorHora = s; }

    public int  getHorasMes()          { return horasMes; }
    public void setHorasMes(int h)     { this.horasMes = h; }
}