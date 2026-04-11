package modelo;


public class Profesor extends Persona {

    // ─────────────────────────────────────────────────────────────
    // ATRIBUTOS
    // ─────────────────────────────────────────────────────────────

    /** Cédula del profesor */
    private String cedula;

    /** Área académica del profesor */
    private Area area;

    /** Salario por hora trabajada */
    private double salarioPorHora;

    /** Número de horas trabajadas en el mes */
    private int horasMes;

    // ─────────────────────────────────────────────────────────────
    // CONSTRUCTOR
    // ─────────────────────────────────────────────────────────────

    /**
     * Constructor de la clase Profesor.
     *
     * @param nombre Nombre del profesor
     * @param direccion Dirección
     * @param telefono Teléfono
     * @param fechaNacimiento Fecha de nacimiento
     * @param cedula Cédula
     * @param area Área académica
     * @param salarioPorHora Pago por hora
     * @param horasMes Horas trabajadas al mes
     */
    public Profesor(String nombre, String direccion,
                    String telefono, String fechaNacimiento,
                    String cedula, Area area,
                    double salarioPorHora, int horasMes) {

        super(nombre, direccion, telefono, fechaNacimiento);
        this.cedula = cedula;
        this.area = area;
        this.salarioPorHora = salarioPorHora;
        this.horasMes = horasMes;
    }

    // ─────────────────────────────────────────────────────────────
    // MÉTODOS DE NEGOCIO
    // ─────────────────────────────────────────────────────────────

    /**
     * Calcula el pago total sin horas extra.
     *
     * @return pago total del profesor
     */
    public double calcularPago() {
        double base = salarioPorHora * horasMes;
        return base * 1.30;
    }

    /**
     * Obtiene el pago total (alias de calcularPago).
     *
     * @return pago total
     */
    public double getPagoTotal() {
        return calcularPago();
    }

    /**
     * Calcula el pago total incluyendo horas extra.
     *
     * @param horasExtra cantidad de horas adicionales
     * @return pago total con horas extra
     */
    public double calcularPago(int horasExtra) {
        double base = salarioPorHora * (horasMes + horasExtra);
        return base * 1.30;
    }

    /**
     * Calcula las prestaciones sociales (19% del pago).
     *
     * @return valor de las prestaciones
     */
    public double calcularPrestaciones() {
        return calcularPago() * 0.19;
    }

    // ─────────────────────────────────────────────────────────────
    // POLIMORFISMO (SOBREESCRITURA)
    // ─────────────────────────────────────────────────────────────

    /**
     * Muestra la información del profesor en formato personalizado.
     *
     * @return cadena con los datos del profesor
     */
    @Override
    public String mostrarInfo() {
        return String.format(
            "PROFESOR  | %-20s | CC: %-12s | Área: %-15s | Pago: $%,.2f | Prestaciones: $%,.2f",
            getNombre(), cedula, area.getNombre(),
            calcularPago(), calcularPrestaciones()
        );
    }

    /**
     * Representación simplificada del profesor.
     *
     * @return nombre y área
     */
    @Override
    public String toString() {
        return getNombre() + " — " + area.getNombre();
    }

    // ─────────────────────────────────────────────────────────────
    // GETTERS Y SETTERS
    // ─────────────────────────────────────────────────────────────

    /**
     * Obtiene la cédula.
     * @return cédula del profesor
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Modifica la cédula.
     * @param c nueva cédula
     */
    public void setCedula(String c) {
        this.cedula = c;
    }

    /**
     * Obtiene el área.
     * @return área académica
     */
    public Area getArea() {
        return area;
    }

    /**
     * Modifica el área.
     * @param a nueva área
     */
    public void setArea(Area a) {
        this.area = a;
    }

    /**
     * Obtiene el salario por hora.
     * @return salario por hora
     */
    public double getSalarioPorHora() {
        return salarioPorHora;
    }

    /**
     * Modifica el salario por hora.
     * @param s nuevo salario
     */
    public void setSalarioPorHora(double s) {
        this.salarioPorHora = s;
    }

    /**
     * Obtiene las horas mensuales.
     * @return horas trabajadas
     */
    public int getHorasMes() {
        return horasMes;
    }

    /**
     * Modifica las horas mensuales.
     * @param h nuevas horas
     */
    public void setHorasMes(int h) {
        this.horasMes = h;
    }
}