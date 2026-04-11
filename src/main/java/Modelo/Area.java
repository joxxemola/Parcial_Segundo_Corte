package modelo;

/**
 * Clase Area — representa un área académica dentro del sistema.
 *
 * <p>Un área académica define la especialidad o campo de enseñanza
 * de un profesor, como por ejemplo: Matemáticas, Ciencias, Lenguaje, etc.</p>
 *
 * <p><b>Relación:</b>
 * Existe una relación de asociación (o composición) donde
 * un Profesor tiene un objeto de tipo Area.</p>
 *
 * <p><b>Responsabilidades:</b>
 * <ul>
 *   <li>Almacenar el nombre del área.</li>
 *   <li>Describir el área académica.</li>
 * </ul>
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Area {

    /** Nombre del área académica */
    private String nombre;

    /** Descripción del área */
    private String descripcion;

    /**
     * Constructor de la clase Area.
     *
     * @param nombre Nombre del área académica
     * @param descripcion Descripción del área
     */
    public Area(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // ─────────────────────────────────────────────────────────────
    // GETTERS Y SETTERS
    // ─────────────────────────────────────────────────────────────

    /**
     * Obtiene el nombre del área.
     *
     * @return nombre del área
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del área.
     *
     * @param n nuevo nombre del área
     */
    public void setNombre(String n) {
        this.nombre = n;
    }

    /**
     * Obtiene la descripción del área.
     *
     * @return descripción del área
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripción del área.
     *
     * @param desc nueva descripción del área
     */
    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    /**
     * Representación en texto del área.
     *
     * @return cadena con el nombre y la descripción
     */
    @Override
    public String toString() {
        return nombre + " (" + descripcion + ")";
    }
}