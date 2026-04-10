package Controlador;

import modelo.Estudiante;
import modelo.Profesor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Clase Colegio — lógica principal.
 *
 * Gestiona las listas de Profesores y Estudiantes,
 * y genera los dos reportes requeridos:
 *   1. Lista de estudiantes.
 *   2. Profesores ordenados por salario total (mayor a menor)
 *      + total acumulado de prestaciones sociales.
 *
 * RELACIÓN con Profesor y Estudiante: DEPENDENCIA (los recibe y usa).
 */
public class Colegio {

    private String nombre;
    private List<Profesor>   profesores  = new ArrayList<>();
    private List<Estudiante> estudiantes = new ArrayList<>();

    // ── Constructor ────────────────────────────────────────────────────────
    public Colegio(String nombre) {
        this.nombre = nombre;
    }

    // ── Administración de datos ───────────────────────────────────────────
    public void agregarProfesor(Profesor p)    { profesores.add(p); }
    public void agregarEstudiante(Estudiante e) { estudiantes.add(e); }

    public boolean eliminarProfesor(String cedula) {
        return profesores.removeIf(p -> p.getCedula().equals(cedula));
    }
    public boolean eliminarEstudiante(String codigo) {
        return estudiantes.removeIf(e -> e.getCodigo().equals(codigo));
    }

    // ── REPORTE 1: Lista de estudiantes ──────────────────────────────────
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

    // ── REPORTE 2: Profesores por salario total (mayor → menor) ──────────
    public String reporteProfesores() {
        List<Profesor> ordenados = new ArrayList<>(profesores);
        ordenados.sort(Comparator.comparingDouble(Profesor::getPagoTotal).reversed());

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

    // ── Getters y Setters ─────────────────────────────────────────────────
    public String getNombre()           { return nombre; }
    public void   setNombre(String n)   { this.nombre = n; }

    public List<Profesor>   getProfesores()   { return profesores; }
    public List<Estudiante> getEstudiantes()  { return estudiantes; }
}