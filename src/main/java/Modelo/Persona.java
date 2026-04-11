package modelo;


public abstract class Persona {

    // ─────────────────────────────────────────────────────────────
    // ATRIBUTOS
    // ─────────────────────────────────────────────────────────────

    /** Nombre completo de la persona */
    private String nombre;

    /** Dirección de residencia */
    private String direccion;

    /** Número de teléfono */
    private String telefono;

    /** Fecha de nacimiento */
    private String fechaNacimiento;

    // ─────────────────────────────────────────────────────────────
    // CONSTRUCTORES
    // ─────────────────────────────────────────────────────────────

    /**
     * Constructor completo de la clase Persona.
     *
     * @param nombre Nombre de la persona
     * @param direccion Dirección de residencia
     * @param telefono Número de contacto
     * @param fechaNacimiento Fecha de nacimiento
     */
    public Persona(String nombre, String direccion,
                   String telefono, String fechaNacimiento) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Constructor vacío.
     *
     * <p>Se incluye para permitir que subclases como {@link Estudiante}
     * puedan inicializarse sin parámetros y usar setters posteriormente.</p>
     */
    public Persona() {
        this("", "", "", "");
    }

    // ─────────────────────────────────────────────────────────────
    // MÉTODOS ABSTRACTOS
    // ─────────────────────────────────────────────────────────────

    /**
     * Método abstracto que debe ser implementado por las subclases.
     *
     * <p>Cada tipo de persona (Profesor o Estudiante) define
     * su propia forma de mostrar la información.</p>
     *
     * @return cadena con la información formateada
     */
    public abstract String mostrarInfo();

    // ─────────────────────────────────────────────────────────────
    // MÉTODOS SOBRESCRITOS
    // ─────────────────────────────────────────────────────────────

    /**
     * Representación básica en texto de la persona.
     *
     * @return nombre, teléfono y fecha de nacimiento
     */
    @Override
    public String toString() {
        return nombre + " | Tel: " + telefono + " | Nac: " + fechaNacimiento;
    }

    // ─────────────────────────────────────────────────────────────
    // GETTERS Y SETTERS
    // ─────────────────────────────────────────────────────────────

    /**
     * Obtiene el nombre.
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre.
     * @param n nuevo nombre
     */
    public void setNombre(String n) {
        this.nombre = n;
    }

    /**
     * Obtiene la dirección.
     * @return dirección de residencia
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la dirección.
     * @param d nueva dirección
     */
    public void setDireccion(String d) {
        this.direccion = d;
    }

    /**
     * Obtiene el teléfono.
     * @return número de teléfono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el teléfono.
     * @param t nuevo teléfono
     */
    public void setTelefono(String t) {
        this.telefono = t;
    }

    /**
     * Obtiene la fecha de nacimiento.
     * @return fecha de nacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Modifica la fecha de nacimiento.
     * @param fecha nueva fecha
     */
    public void setFechaNacimiento(String fecha) {
        this.fechaNacimiento = fecha;
    }
}