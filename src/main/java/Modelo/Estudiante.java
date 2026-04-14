package modelo;

public class Estudiante extends Persona {

    // ─────────────────────────────────────────────────────────────
    // ATRIBUTOS
    // ─────────────────────────────────────────────────────────────

    /** Código único del estudiante */
    private String codigo;

    /** Grado académico del estudiante */
    private String grado;

    /** Nombre del acudiente o responsable */
    private String acudiente;
 

    // ─────────────────────────────────────────────────────────────
    // MÉTODOS
    // ─────────────────────────────────────────────────────────────


    @Override
    public String mostrarInfo() {
        return String.format(
            "ESTUDIANTE | %-20s | Código: %-8s | Grado: %-6s | Acudiente: %s",
            getNombre(), codigo, grado, acudiente
        );
    }

    /**
     * Representación simplificada del estudiante.
     *
     * @return nombre y grado del estudiante
     */
    @Override
    public String toString() {
        return getNombre() + " — Grado " + grado;
    }

    // ─────────────────────────────────────────────────────────────
    // GETTERS Y SETTERS
    // ─────────────────────────────────────────────────────────────

    /**
     * Obtiene el código del estudiante.
     *
     * @return código del estudiante
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Asigna el código del estudiante.
     *
     * @param c nuevo código
     */
    public void setCodigo(String c) {
        this.codigo = c;
    }

    /**
     * Obtiene el grado del estudiante.
     *
     * @return grado académico
     */
    public String getGrado() {
        return grado;
    }

    /**
     * Asigna el grado del estudiante.
     *
     * @param g nuevo grado
     */
    public void setGrado(String g) {
        this.grado = g;
    }

    /**
     * Obtiene el acudiente del estudiante.
     *
     * @return nombre del acudiente
     */
    public String getAcudiente() {
        return acudiente;
    }

    /**
     * Asigna el acudiente del estudiante.
     *
     * @param a nuevo acudiente
     */
    public void setAcudiente(String a) {
        this.acudiente = a;
    }
}