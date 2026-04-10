package test;

import modelo.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TEST UNITARIO — clase Estudiante
 *
 * Valida que los setters/getters almacenen correctamente los atributos,
 * y que mostrarInfo() retorne la información del estudiante (polimorfismo).
 *
 * NOTA: Estudiante no tiene constructor explícito; se carga mediante setters.
 */
@DisplayName("Tests Unitarios – Estudiante")
class EstudianteTest {

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante();          // constructor vacío heredado
        estudiante.setNombre("Juan Torres");
        estudiante.setDireccion("Cll 20 #3-10");
        estudiante.setTelefono("3104445566");
        estudiante.setFechaNacimiento("2008-05-10");
        estudiante.setCodigo("E001");
        estudiante.setGrado("8°");
        estudiante.setAcudiente("María Torres");
    }

    @Test
    @DisplayName("GET nombre — debe retornar el nombre asignado")
    void testGetNombre() {
        assertEquals("Juan Torres", estudiante.getNombre());
    }

    @Test
    @DisplayName("GET codigo — debe retornar el código asignado")
    void testGetCodigo() {
        assertEquals("E001", estudiante.getCodigo());
    }

    @Test
    @DisplayName("GET grado — debe retornar el grado asignado")
    void testGetGrado() {
        assertEquals("8°", estudiante.getGrado());
    }

    @Test
    @DisplayName("GET acudiente — debe retornar el acudiente asignado")
    void testGetAcudiente() {
        assertEquals("María Torres", estudiante.getAcudiente());
    }

    @Test
    @DisplayName("SET codigo — debe actualizar el código")
    void testSetCodigo() {
        estudiante.setCodigo("E099");
        assertEquals("E099", estudiante.getCodigo());
    }

    @Test
    @DisplayName("mostrarInfo() contiene nombre y código (polimorfismo)")
    void testMostrarInfo() {
        String info = estudiante.mostrarInfo();
        assertTrue(info.contains("Juan Torres"), "Debe contener el nombre");
        assertTrue(info.contains("E001"),        "Debe contener el código");
        assertTrue(info.contains("8°"),          "Debe contener el grado");
    }

    @Test
    @DisplayName("mostrarInfo() incluye etiqueta ESTUDIANTE")
    void testMostrarInfoEtiqueta() {
        assertTrue(estudiante.mostrarInfo().contains("ESTUDIANTE"),
                "La etiqueta diferenciadora debe estar presente");
    }

    @Test
    @DisplayName("Atributos Persona se asignan correctamente")
    void testAtributosPersona() {
        assertEquals("Cll 20 #3-10",  estudiante.getDireccion());
        assertEquals("3104445566",     estudiante.getTelefono());
        assertEquals("2008-05-10",     estudiante.getFechaNacimiento());
    }
}