package vista;

import Controlador.Colegio;
import modelo.Area;
import modelo.Estudiante;
import modelo.Profesor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class MainFrame extends JFrame {

    // ─────────────────────────────────────────────────────────────
    // MODELO
    // ─────────────────────────────────────────────────────────────

    /** Instancia del colegio (modelo principal del sistema) */
    private final Colegio colegio = new Colegio("Colegio UAO");

    // ─────────────────────────────────────────────────────────────
    // COMPONENTES GENERALES
    // ─────────────────────────────────────────────────────────────

    private JTabbedPane tabbedPane;

    // ─────────────────────────────────────────────────────────────
    // TAB PROFESORES
    // ─────────────────────────────────────────────────────────────

    private JTextField txtNombreP, txtDirP, txtTelP, txtNacP;
    private JTextField txtCedula, txtArea, txtDescArea, txtSalHora, txtHoras;
    private DefaultTableModel modeloTablaP;
    private JTable tablaP;

    // ─────────────────────────────────────────────────────────────
    // TAB ESTUDIANTES
    // ─────────────────────────────────────────────────────────────

    private JTextField txtNombreE, txtDirE, txtTelE, txtNacE;
    private JTextField txtCodigo, txtGrado, txtAcudiente;
    private DefaultTableModel modeloTablaE;
    private JTable tablaE;

    // ─────────────────────────────────────────────────────────────
    // TAB REPORTES
    // ─────────────────────────────────────────────────────────────

    private JTextArea areaReporte;

    // ─────────────────────────────────────────────────────────────
    // CONSTRUCTOR
    // ─────────────────────────────────────────────────────────────

    /**
     * Constructor principal.
     *
     * <p>Inicializa la interfaz gráfica, carga datos de ejemplo
     * y actualiza las tablas.</p>
     */
    public MainFrame() {
        initComponents();
        cargarDatosDemostracion();
        actualizarTablas();
    }

    // ─────────────────────────────────────────────────────────────
    // INICIALIZACIÓN DE LA INTERFAZ
    // ─────────────────────────────────────────────────────────────

    /**
     * Inicializa los componentes de la interfaz gráfica.
     */
    private void initComponents() {
        setTitle("Sistema de Gestión Escolar — " + colegio.getNombre());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("👨‍🏫 Profesores", buildTabProfesores());
        tabbedPane.addTab("🎓 Estudiantes", buildTabEstudiantes());
        tabbedPane.addTab("📊 Reportes", buildTabReportes());

        add(tabbedPane);
    }

    // ─────────────────────────────────────────────────────────────
    // ACCIONES (EVENTOS)
    // ─────────────────────────────────────────────────────────────

    /**
     * Agrega un profesor al sistema a partir de los datos del formulario.
     */
    private void agregarProfesor() {
        try {
            Area area = new Area(txtArea.getText(), txtDescArea.getText());

            Profesor p = new Profesor(
                    txtNombreP.getText(), txtDirP.getText(),
                    txtTelP.getText(), txtNacP.getText(),
                    txtCedula.getText(), area,
                    Double.parseDouble(txtSalHora.getText()),
                    Integer.parseInt(txtHoras.getText())
            );

            colegio.agregarProfesor(p);
            actualizarTablas();
            limpiarFormProfesor();

            JOptionPane.showMessageDialog(this, "Profesor agregado correctamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Salario y horas deben ser numéricos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina el profesor seleccionado en la tabla.
     */
    private void eliminarProfesor() {
        int fila = tablaP.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un profesor.");
            return;
        }

        String cedula = (String) modeloTablaP.getValueAt(fila, 1);
        colegio.eliminarProfesor(cedula);
        actualizarTablas();
    }

    /**
     * Agrega un estudiante al sistema.
     */
    private void agregarEstudiante() {
        try {
            Estudiante e = new Estudiante();

            e.setNombre(txtNombreE.getText());
            e.setDireccion(txtDirE.getText());
            e.setTelefono(txtTelE.getText());
            e.setFechaNacimiento(txtNacE.getText());
            e.setCodigo(txtCodigo.getText());
            e.setGrado(txtGrado.getText());
            e.setAcudiente(txtAcudiente.getText());

            colegio.agregarEstudiante(e);
            actualizarTablas();
            limpiarFormEstudiante();

            JOptionPane.showMessageDialog(this, "Estudiante agregado correctamente.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina el estudiante seleccionado.
     */
    private void eliminarEstudiante() {
        int fila = tablaE.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante.");
            return;
        }

        String codigo = (String) modeloTablaE.getValueAt(fila, 1);
        colegio.eliminarEstudiante(codigo);
        actualizarTablas();
    }

    // ─────────────────────────────────────────────────────────────
    // ACTUALIZACIÓN DE TABLAS
    // ─────────────────────────────────────────────────────────────

    /**
     * Refresca los datos mostrados en las tablas de profesores y estudiantes.
     */
    private void actualizarTablas() {
        modeloTablaP.setRowCount(0);

        for (Profesor p : colegio.getProfesores()) {
            modeloTablaP.addRow(new Object[]{
                    p.getNombre(),
                    p.getCedula(),
                    p.getArea().getNombre(),
                    p.getSalarioPorHora(),
                    p.getHorasMes(),
                    String.format("$%,.2f", p.calcularPago()),
                    String.format("$%,.2f", p.calcularPrestaciones())
            });
        }

        modeloTablaE.setRowCount(0);

        for (Estudiante e : colegio.getEstudiantes()) {
            modeloTablaE.addRow(new Object[]{
                    e.getNombre(),
                    e.getCodigo(),
                    e.getGrado(),
                    e.getAcudiente(),
                    e.getTelefono()
            });
        }
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN
    // ─────────────────────────────────────────────────────────────

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            new MainFrame().setVisible(true);
        });
    }

    private void limpiarFormEstudiante() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Component buildTabProfesores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Component buildTabEstudiantes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Component buildTabReportes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarDatosDemostracion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void limpiarFormProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}