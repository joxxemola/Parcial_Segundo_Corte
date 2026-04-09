package modelo;

/**
 * Clase Area — representa el área académica de un Profesor.
 * Relación: Profesor TIENE-UN Area  (asociación / composición)
 */
public class Area {

    private String nombre;
    private String descripcion;

    // ── Constructor ────────────────────────────────────────────────────────
    public Area(String nombre, String descripcion) {
        this.nombre      = nombre;
        this.descripcion = descripcion;
    }

    // ── Getters y Setters ─────────────────────────────────────────────────
    public String getNombre()              { return nombre; }
    public void   setNombre(String n)      { this.nombre = n; }

    public String getDescripcion()             { return descripcion; }
    public void   setDescripcion(String desc)  { this.descripcion = desc; }

    @Override
    public String toString() {
        return nombre + " (" + descripcion + ")";
    }
}