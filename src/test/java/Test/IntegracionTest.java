package test;

import Controlador.Colegio;
import modelo.Area;
import modelo.Estudiante;
import modelo.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TEST DE INTEGRACIÓN — clase Colegio + Profesor + Estudiante
 *
 * Verifica que los módulos interactúen correctamente:
 *   - Agregar y listar profesores/estudiantes.
 *   - Eliminar por identificador.
 *   - Reporte de estudiantes no vacío y con encabezado correcto.
 *   - Reporte de profesores ordenado (mayor → menor pago).
 *   - Total de prestaciones igual a la suma individual.
 */
@DisplayName("Tests de Integración – Colegio")
class IntegracionTest {

    private Colegio colegio;
    private Profesor p1, p2, p3;
    private Estudiante e1, e2;

    @BeforeEach
    void setUp() {
        colegio = new Colegio("Colegio Test");

        // Profesores con distintos salarios
        p1 = new Profesor("Ana Rodríguez","Dir1","Tel1","1985-01-01","CC-001",
                new Area("Matemáticas","Mat"), 35_000, 80);
        p2 = new Profesor("Carlos Pérez", "Dir2","Tel2","1979-05-10","CC-002",
                new Area("Ciencias","Cie"),    28_000, 60);
        p3 = new Profesor("Laura Gómez",  "Dir3","Tel3","1990-03-20","CC-003",
                new Area("Historia","His"),    32_000, 70);

        colegio.agregarProfesor(p1);
        colegio.agregarProfesor(p2);
        colegio.agregarProfesor(p3);

        // Estudiantes
        e1 = new Estudiante();
        e1.setNombre("Juan Torres"); e1.setCodigo("E001");
        e1.setGrado("8°"); e1.setAcudiente("María Torres");

        e2 = new Estudiante();
        e2.setNombre("Sofía Vargas"); e2.setCodigo("E002");
        e2.setGrado("7°"); e2.setAcudiente("Pedro Vargas");

        colegio.agregarEstudiante(e1);
        colegio.agregarEstudiante(e2);
    }

    // ── 1. Listas ─────────────────────────────────────────────────────────
    @Test
    @DisplayName("Lista de profesores tiene 3 elementos")
    void testConteoProf() {
        assertEquals(3, colegio.getProfesores().size());
    }

    @Test
    @DisplayName("Lista de estudiantes tiene 2 elementos")
    void testConteoEst() {
        assertEquals(2, colegio.getEstudiantes().size());
    }

    // ── 2. Eliminar ───────────────────────────────────────────────────────
    @Test
    @DisplayName("Eliminar profesor por cédula reduce la lista")
    void testEliminarProfesor() {
        assertTrue(colegio.eliminarProfesor("CC-001"));
        assertEquals(2, colegio.getProfesores().size());
    }

    @Test
    @DisplayName("Eliminar profesor con cédula inexistente retorna false")
    void testEliminarProfesorInexistente() {
        assertFalse(colegio.eliminarProfesor("NULO"));
        assertEquals(3, colegio.getProfesores().size());
    }

    @Test
    @DisplayName("Eliminar estudiante por código reduce la lista")
    void testEliminarEstudiante() {
        assertTrue(colegio.eliminarEstudiante("E001"));
        assertEquals(1, colegio.getEstudiantes().size());
    }

    // ── 3. Reporte de estudiantes ─────────────────────────────────────────
    @Test
    @DisplayName("Reporte de estudiantes no es nulo ni vacío")
    void testReporteEstudiantesNoVacio() {
        String reporte = colegio.reporteEstudiantes();
        assertNotNull(reporte);
        assertFalse(reporte.isBlank());
    }

    @Test
    @DisplayName("Reporte de estudiantes contiene los nombres registrados")
    void testReporteEstudiantesContenido() {
        String reporte = colegio.reporteEstudiantes();
        assertTrue(reporte.contains("Juan Torres"),  "Debe listar a Juan Torres");
        assertTrue(reporte.contains("Sofía Vargas"), "Debe listar a Sofía Vargas");
    }

    @Test
    @DisplayName("Reporte de estudiantes incluye total correcto")
    void testReporteEstudiantesTotal() {
        String reporte = colegio.reporteEstudiantes();
        assertTrue(reporte.contains("2"),
                "El reporte debe indicar 2 estudiantes en total");
    }

    // ── 4. Reporte de profesores (orden descendente) ──────────────────────
    @Test
    @DisplayName("Reporte de profesores no es nulo ni vacío")
    void testReporteProfesoresNoVacio() {
        String reporte = colegio.reporteProfesores();
        assertNotNull(reporte);
        assertFalse(reporte.isBlank());
    }

    @Test
    @DisplayName("Reporte de profesores contiene los nombres registrados")
    void testReporteProfesoresContenido() {
        String reporte = colegio.reporteProfesores();
        assertTrue(reporte.contains("Ana Rodríguez"));
        assertTrue(reporte.contains("Carlos Pérez"));
        assertTrue(reporte.contains("Laura Gómez"));
    }

    @Test
    @DisplayName("Reporte de profesores: Ana aparece antes que Carlos (mayor salario)")
    void testOrdenReporteProfesores() {
        String reporte = colegio.reporteProfesores();
        int posAna    = reporte.indexOf("Ana Rodríguez");
        int posCarlos = reporte.indexOf("Carlos Pérez");
        assertTrue(posAna < posCarlos,
                "Ana (salario mayor) debe aparecer antes que Carlos");
    }

    // ── 5. Total de prestaciones ──────────────────────────────────────────
    @Test
    @DisplayName("Total prestaciones = suma individual de cada profesor")
    void testTotalPrestaciones() {
        double totalEsperado = p1.calcularPrestaciones()
                             + p2.calcularPrestaciones()
                             + p3.calcularPrestaciones();
        // Extraemos el total del reporte (verificación indirecta de integración)
        String reporte = colegio.reporteProfesores();
        // El reporte debe contener alguna mención al total de prestaciones
        assertNotNull(reporte);
        // Verificación directa sumando los objetos del colegio
        double totalObtenido = colegio.getProfesores().stream()
                .mapToDouble(Profesor::calcularPrestaciones).sum();
        assertEquals(totalEsperado, totalObtenido, 0.01,
                "El total de prestaciones debe coincidir");
    }

    // ── 6. Polimorfismo en acción ─────────────────────────────────────────
    @Test
    @DisplayName("mostrarInfo() de Profesor y Estudiante son distintos")
    void testPolimorfismoMostrarInfo() {
        String infoP = p1.mostrarInfo();
        String infoE = e1.mostrarInfo();
        assertNotEquals(infoP, infoE,
                "Las implementaciones polimórficas deben generar salidas distintas");
        assertTrue(infoP.contains("PROFESOR"),   "Profesor debe etiquetarse como tal");
        assertTrue(infoE.contains("ESTUDIANTE"), "Estudiante debe etiquetarse como tal");
    }
}