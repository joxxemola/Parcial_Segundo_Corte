package Controlador;

import modelo.Estudiante;
import modelo.Profesor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Clase Colegio — lógica principal del sistema.
 *
 * <p>Esta clase representa un colegio y se encarga de gestionar
 * la información de los profesores y estudiantes registrados.</p>
 *
 * <p>Permite:
 * <ul>
 *   <li>Agregar y eliminar profesores.</li>
 *   <li>Agregar y eliminar estudiantes.</li>
 *   <li>Generar reportes organizados en formato texto.</li>
 * </ul>
 * </p>
 *
 * <p><b>Relaciones:</b>
 * <ul>
 *   <li>Dependencia con {@link Profesor}</li>
 *   <li>Dependencia con {@link Estudiante}</li>
 * </ul>
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Colegio {

    /** Nombre del colegio */
    private String nombre;

    /** Lista de profesores registrados */
    private List<Profesor> profesores = new ArrayList<>();

    /** Lista de estudiantes registrados */
    private List<Estudiante> estudiantes = new ArrayList<>();

    /**
     * Constructor de la clase Colegio.
     *
     * @param nombre Nombre del colegio
     */
    public Colegio(String nombre) {
        this.nombre = nombre;
    }

    // ─────────────────────────────────────────────────────────────
    // MÉTODOS DE ADMINISTRACIÓN
    // ─────────────────────────────────────────────────────────────

    /**
     * Agrega un profesor a la lista.
     *
     * @param p Objeto de tipo Profesor
     */
    public void agregarProfesor(Profesor p) {
        profesores.add(p);
    }

    /**
     * Agrega un estudiante a la lista.
     *
     * @param e Objeto de tipo Estudiante
     */
    public void agregarEstudiante(Estudiante e) {
        estudiantes.add(e);
    }

    /**
     * Elimina un profesor según su cédula.
     *
     * @param cedula Cédula del profesor
     * @return true si fue eliminado, false si no se encontró
     */
    public boolean eliminarProfesor(String cedula) {
        return profesores.removeIf(p -> p.getCedula().equals(cedula));
    }

    /**
     * Elimina un estudiante según su código.
     *
     * @param codigo Código del estudiante
     * @return true si fue eliminado, false si no se encontró
     */
    public boolean eliminarEstudiante(String codigo) {
        return estudiantes.removeIf(e -> e.getCodigo().equals(codigo));
    }

    // ─────────────────────────────────────────────────────────────
    // REPORTES
    // ─────────────────────────────────────────────────────────────

    /**
     * Genera un reporte en formato tabla con la lista de estudiantes.
     *
     * <p>Incluye:
     * <ul>
     *   <li>Nombre</li>
     *   <li>Código</li>
     *   <li>Grado</li>
     *   <li>Acudiente</li>
     * </ul>
     * </p>
     *
     * @return Cadena de texto con el reporte formateado
     */
    public String reporteEstudiantes() {
        StringBuilder sb = new StringBuilder();

        sb.append("╔══════════════════════════════════════════════════════════════════╗\n");
        sb.append("║          REPORTE DE ESTUDIANTES — ").append(nombre).append("             ║\n");
        sb.append("╠══════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-4s ║ %-20s ║ %-8s ║ %-6s ║ %-12s ║%n",
                "#", "NOMBRE", "CÓDIGO", "GRADO", "ACUDIENTE"));
        sb.append("╠══════════════════════════════════════════════════════════════════╣\n");

        int i = 1;
        for (Estudiante e : estudiantes) {
            sb.append(String.format("║ %-4d ║ %-20s ║ %-8s ║ %-6s ║ %-12s ║%n",
                    i++, e.getNombre(), e.getCodigo(),
                    e.getGrado(), e.getAcudiente()));
        }

        sb.append("╠══════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║  Total estudiantes: %-46d ║%n", estudiantes.size()));
        sb.append("╚══════════════════════════════════════════════════════════════════╝\n");

        return sb.toString();
    }

    /**
     * Genera un reporte de profesores ordenados por salario total (de mayor a menor).
     *
     * <p>Incluye:
     * <ul>
     *   <li>Nombre</li>
     *   <li>Área</li>
     *   <li>Pago total</li>
     *   <li>Prestaciones sociales</li>
     * </ul>
     * </p>
     *
     * <p>También calcula el total acumulado de prestaciones sociales.</p>
     *
     * @return Cadena de texto con el reporte formateado
     */
    public String reporteProfesores() {

        // Copia para no modificar la lista original
        List<Profesor> ordenados = new ArrayList<>(profesores);

        // Ordenar de mayor a menor según pago total
        ordenados.sort(Comparator.comparingDouble(Profesor::getPagoTotal).reversed());

        // Calcular total de prestaciones
        double totalPrestaciones = ordenados.stream()
                .mapToDouble(Profesor::calcularPrestaciones)
                .sum();

        StringBuilder sb = new StringBuilder();

        sb.append("╔══════════════════════════════════════════════════════════════════════════╗\n");
        sb.append("║     REPORTE DE PROFESORES (Mayor a menor salario) — ").append(nombre).append("  ║\n");
        sb.append("╠══════════════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-4s ║ %-18s ║ %-12s ║ %-12s ║ %-13s ║%n",
                "#", "NOMBRE", "ÁREA", "PAGO TOTAL", "PRESTACIONES"));
        sb.append("╠══════════════════════════════════════════════════════════════════════════╣\n");

        int i = 1;
        for (Profesor p : ordenados) {
            sb.append(String.format("║ %-4d ║ %-18s ║ %-12s ║ $%,-11.2f ║ $%,-12.2f ║%n",
                    i++, p.getNombre(), p.getArea().getNombre(),
                    p.calcularPago(), p.calcularPrestaciones()));
        }

        sb.append("╠══════════════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║  Total profesores: %-3d  |  TOTAL PRESTACIONES: $%,-24.2f ║%n",
                profesores.size(), totalPrestaciones));
        sb.append("╚══════════════════════════════════════════════════════════════════════════╝\n");

        return sb.toString();
    }

    // ─────────────────────────────────────────────────────────────
    // GETTERS Y SETTERS
    // ─────────────────────────────────────────────────────────────

    /**
     * Obtiene el nombre del colegio.
     * @return nombre del colegio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del colegio.
     * @param n nuevo nombre
     */
    public void setNombre(String n) {
        this.nombre = n;
    }

    /**
     * Obtiene la lista de profesores.
     * @return lista de profesores
     */
    public List<Profesor> getProfesores() {
        return profesores;
    }

    /**
     * Obtiene la lista de estudiantes.
     * @return lista de estudiantes
     */
    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}