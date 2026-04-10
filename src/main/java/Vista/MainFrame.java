package vista;

import Controlador.Colegio;
import modelo.Area;
import modelo.Estudiante;
import modelo.Profesor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * MainFrame — Interfaz gráfica principal (Swing / NetBeans compatible).
 *
 * Estructura:
 *   JTabbedPane
 *     ├── Tab 1: Profesores    (formulario + tabla)
 *     ├── Tab 2: Estudiantes   (formulario + tabla)
 *     └── Tab 3: Reportes      (área de texto + botones)
 */
public class MainFrame extends JFrame {

    // ── Modelo de datos ───────────────────────────────────────────────────
    private final Colegio colegio = new Colegio("Colegio UAO");

    // ── Componentes comunes ───────────────────────────────────────────────
    private JTabbedPane tabbedPane;

    // ── Tab Profesores ────────────────────────────────────────────────────
    private JTextField txtNombreP, txtDirP, txtTelP, txtNacP;
    private JTextField txtCedula, txtArea, txtDescArea, txtSalHora, txtHoras;
    private DefaultTableModel modeloTablaP;
    private JTable tablaP;

    // ── Tab Estudiantes ───────────────────────────────────────────────────
    private JTextField txtNombreE, txtDirE, txtTelE, txtNacE;
    private JTextField txtCodigo, txtGrado, txtAcudiente;
    private DefaultTableModel modeloTablaE;
    private JTable tablaE;

    // ── Tab Reportes ──────────────────────────────────────────────────────
    private JTextArea areaReporte;

    // ══════════════════════════════════════════════════════════════════════
    public MainFrame() {
        initComponents();
        cargarDatosDemostracion();
        actualizarTablas();
    }

    // ── Construcción de la UI ─────────────────────────────────────────────
    private void initComponents() {
        setTitle("Sistema de Gestión Escolar — " + colegio.getNombre());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("👨‍🏫 Profesores",  buildTabProfesores());
        tabbedPane.addTab("🎓 Estudiantes", buildTabEstudiantes());
        tabbedPane.addTab("📊 Reportes",    buildTabReportes());

        add(tabbedPane);
    }

    // ── TAB PROFESORES ────────────────────────────────────────────────────
    private JPanel buildTabProfesores() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // Formulario
        JPanel form = new JPanel(new GridLayout(0, 4, 4, 4));
        form.setBorder(BorderFactory.createTitledBorder("Datos del Profesor"));

        txtNombreP = new JTextField(); txtDirP = new JTextField();
        txtTelP    = new JTextField(); txtNacP  = new JTextField();
        txtCedula  = new JTextField(); txtArea  = new JTextField();
        txtDescArea= new JTextField(); txtSalHora= new JTextField();
        txtHoras   = new JTextField();

        addField(form, "Nombre:",        txtNombreP);
        addField(form, "Dirección:",     txtDirP);
        addField(form, "Teléfono:",      txtTelP);
        addField(form, "Fecha Nac.:",    txtNacP);
        addField(form, "Cédula:",        txtCedula);
        addField(form, "Área:",          txtArea);
        addField(form, "Desc. Área:",    txtDescArea);
        addField(form, "Salario/Hora ($):",  txtSalHora);
        addField(form, "Horas/Mes:",     txtHoras);

        JButton btnAgregar  = new JButton("➕ Agregar");
        JButton btnEliminar = new JButton("🗑 Eliminar");
        form.add(btnAgregar);
        form.add(btnEliminar);

        btnAgregar.addActionListener(e -> agregarProfesor());
        btnEliminar.addActionListener(e -> eliminarProfesor());

        // Tabla
        String[] cols = {"Nombre","Cédula","Área","Sal/Hora","Horas","Pago Total","Prestaciones"};
        modeloTablaP = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaP = new JTable(modeloTablaP);
        tablaP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaP.getTableHeader().setReorderingAllowed(false);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaP), BorderLayout.CENTER);
        return panel;
    }

    // ── TAB ESTUDIANTES ───────────────────────────────────────────────────
    private JPanel buildTabEstudiantes() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel form = new JPanel(new GridLayout(0, 4, 4, 4));
        form.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        txtNombreE  = new JTextField(); txtDirE     = new JTextField();
        txtTelE     = new JTextField(); txtNacE     = new JTextField();
        txtCodigo   = new JTextField(); txtGrado    = new JTextField();
        txtAcudiente= new JTextField();

        addField(form, "Nombre:",     txtNombreE);
        addField(form, "Dirección:",  txtDirE);
        addField(form, "Teléfono:",   txtTelE);
        addField(form, "Fecha Nac.:", txtNacE);
        addField(form, "Código:",     txtCodigo);
        addField(form, "Grado:",      txtGrado);
        addField(form, "Acudiente:",  txtAcudiente);

        JButton btnAgregar  = new JButton("➕ Agregar");
        JButton btnEliminar = new JButton("🗑 Eliminar");
        form.add(btnAgregar);
        form.add(btnEliminar);

        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());

        String[] cols = {"Nombre","Código","Grado","Acudiente","Teléfono"};
        modeloTablaE = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaE = new JTable(modeloTablaE);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaE), BorderLayout.CENTER);
        return panel;
    }

    // ── TAB REPORTES ──────────────────────────────────────────────────────
    private JPanel buildTabReportes() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        areaReporte = new JTextArea();
        areaReporte.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        areaReporte.setEditable(false);

        JButton btnEstudiantes = new JButton("📋 Reporte Estudiantes");
        JButton btnProfesores  = new JButton("💰 Reporte Profesores (salario)");
        JButton btnLimpiar     = new JButton("🗑 Limpiar");

        btnEstudiantes.addActionListener(e ->
                areaReporte.setText(colegio.reporteEstudiantes()));
        btnProfesores.addActionListener(e ->
                areaReporte.setText(colegio.reporteProfesores()));
        btnLimpiar.addActionListener(e -> areaReporte.setText(""));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(btnEstudiantes);
        botones.add(btnProfesores);
        botones.add(btnLimpiar);

        panel.add(botones, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaReporte), BorderLayout.CENTER);
        return panel;
    }

    // ── Acciones ──────────────────────────────────────────────────────────
    private void agregarProfesor() {
        try {
            Area area = new Area(txtArea.getText(), txtDescArea.getText());
            Profesor p = new Profesor(
                    txtNombreP.getText(), txtDirP.getText(),
                    txtTelP.getText(),    txtNacP.getText(),
                    txtCedula.getText(),  area,
                    Double.parseDouble(txtSalHora.getText()),
                    Integer.parseInt(txtHoras.getText())
            );
            colegio.agregarProfesor(p);
            actualizarTablas();
            limpiarFormProfesor();
            JOptionPane.showMessageDialog(this, "Profesor agregado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Sal/Hora y Horas deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProfesor() {
        int fila = tablaP.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un profesor."); return; }
        String cedula = (String) modeloTablaP.getValueAt(fila, 1);
        colegio.eliminarProfesor(cedula);
        actualizarTablas();
    }

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
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEstudiante() {
        int fila = tablaE.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un estudiante."); return; }
        String codigo = (String) modeloTablaE.getValueAt(fila, 1);
        colegio.eliminarEstudiante(codigo);
        actualizarTablas();
    }

    // ── Actualizar tablas ─────────────────────────────────────────────────
    private void actualizarTablas() {
        modeloTablaP.setRowCount(0);
        for (Profesor p : colegio.getProfesores()) {
            modeloTablaP.addRow(new Object[]{
                    p.getNombre(), p.getCedula(), p.getArea().getNombre(),
                    p.getSalarioPorHora(), p.getHorasMes(),
                    String.format("$%,.2f", p.calcularPago()),
                    String.format("$%,.2f", p.calcularPrestaciones())
            });
        }

        modeloTablaE.setRowCount(0);
        for (Estudiante e : colegio.getEstudiantes()) {
            modeloTablaE.addRow(new Object[]{
                    e.getNombre(), e.getCodigo(),
                    e.getGrado(), e.getAcudiente(), e.getTelefono()
            });
        }
    }

    // ── Utilidades ────────────────────────────────────────────────────────
    private void addField(JPanel p, String label, JTextField field) {
        p.add(new JLabel(label));
        p.add(field);
    }
    private void limpiarFormProfesor() {
        txtNombreP.setText(""); txtDirP.setText(""); txtTelP.setText(""); txtNacP.setText("");
        txtCedula.setText(""); txtArea.setText(""); txtDescArea.setText("");
        txtSalHora.setText(""); txtHoras.setText("");
    }
    private void limpiarFormEstudiante() {
        txtNombreE.setText(""); txtDirE.setText(""); txtTelE.setText(""); txtNacE.setText("");
        txtCodigo.setText(""); txtGrado.setText(""); txtAcudiente.setText("");
    }

    // ── Datos de demostración ─────────────────────────────────────────────
    private void cargarDatosDemostracion() {
        Area mat = new Area("Matemáticas", "Álgebra y Cálculo");
        Area cien = new Area("Ciencias",   "Biología y Química");
        Area hist = new Area("Historia",   "Ciencias Sociales");

        colegio.agregarProfesor(new Profesor("Ana Rodríguez","Cll 10 #5-20","3001234567","1985-03-15","12345678", mat, 35000, 80));
        colegio.agregarProfesor(new Profesor("Carlos Pérez", "Cra 7 #12-3", "3109876543","1979-07-22","87654321", cien, 28000, 60));
        colegio.agregarProfesor(new Profesor("Laura Gómez", "Cll 45 #9-10","3005556677","1990-11-01","11223344", hist, 32000, 70));

        Estudiante e1 = new Estudiante();
        e1.setNombre("Juan Torres"); e1.setDireccion("Cll 20"); e1.setTelefono("3104445566");
        e1.setFechaNacimiento("2008-05-10"); e1.setCodigo("E001"); e1.setGrado("8°"); e1.setAcudiente("María Torres");

        Estudiante e2 = new Estudiante();
        e2.setNombre("Sofía Vargas"); e2.setDireccion("Cra 15"); e2.setTelefono("3117778899");
        e2.setFechaNacimiento("2009-02-20"); e2.setCodigo("E002"); e2.setGrado("7°"); e2.setAcudiente("Pedro Vargas");

        Estudiante e3 = new Estudiante();
        e3.setNombre("Mateo López"); e3.setDireccion("Av. Simón Bolívar"); e3.setTelefono("3123334455");
        e3.setFechaNacimiento("2007-09-14"); e3.setCodigo("E003"); e3.setGrado("9°"); e3.setAcudiente("Claudia López");

        colegio.agregarEstudiante(e1);
        colegio.agregarEstudiante(e2);
        colegio.agregarEstudiante(e3);
    }

    // ── Main ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new MainFrame().setVisible(true);
        });
    }
}