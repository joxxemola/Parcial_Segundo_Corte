package modelo;

/**
 * Clase Estudiante — hereda de Persona.
 *
 * Por requisito del enunciado esta subclase NO tiene constructor propio;
 * usa el constructor vacío heredado de Persona y se carga con setters.
 *
 * POLIMORFISMO DE SOBREESCRITURA:
 *   mostrarInfo()  — implementación distinta a la de Profesor
 */
public class Estudiante extends Persona {

    // ── Atributos ──────────────────────────────────────────────────────────
    private String codigo;
    private String grado;
    private String acudiente;

    // ── Sin constructor explícito — se usa el de Persona() vacío ──────────
    // El IDE / Java genera automáticamente el constructor por defecto
    // llamando a super() (Persona vacío).

    // ── Sobreescritura de mostrarInfo() ───────────────────────────────────
    @Override
    public String mostrarInfo() {
        return String.format(
            "ESTUDIANTE | %-20s | Código: %-8s | Grado: %-6s | Acudiente: %s",
            getNombre(), codigo, grado, acudiente
        );
    }

    @Override
    public String toString() {
        return getNombre() + " — Grado " + grado;
    }

    // ── Getters y Setters ─────────────────────────────────────────────────
    public String getCodigo()           { return codigo; }
    public void   setCodigo(String c)   { this.codigo = c; }

    public String getGrado()           { return grado; }
    public void   setGrado(String g)   { this.grado = g; }

    public String getAcudiente()           { return acudiente; }
    public void   setAcudiente(String a)   { this.acudiente = a; }
}