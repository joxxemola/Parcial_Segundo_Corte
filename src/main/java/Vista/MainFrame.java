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

    public MainFrame() {
        initComponents();
        cargarDatosDemostracion();
        actualizarTablas();
    }

    // ─────────────────────────────────────────────────────────────
    // INICIALIZACIÓN DE LA INTERFAZ
    // ─────────────────────────────────────────────────────────────

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
    // CONSTRUCCIÓN DE PESTAÑAS
    // ─────────────────────────────────────────────────────────────

    private Component buildTabProfesores() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear campos de texto
        txtNombreP = new JTextField(15);
        txtDirP = new JTextField(15);
        txtTelP = new JTextField(15);
        txtNacP = new JTextField(15);
        txtCedula = new JTextField(15);
        txtArea = new JTextField(15);
        txtDescArea = new JTextField(15);
        txtSalHora = new JTextField(15);
        txtHoras = new JTextField(15);

        // Agregar componentes al formulario
        int row = 0;
        addFormField(formPanel, gbc, "Nombre:", txtNombreP, row++);
        addFormField(formPanel, gbc, "Dirección:", txtDirP, row++);
        addFormField(formPanel, gbc, "Teléfono:", txtTelP, row++);
        addFormField(formPanel, gbc, "Fecha Nacimiento:", txtNacP, row++);
        addFormField(formPanel, gbc, "Cédula:", txtCedula, row++);
        addFormField(formPanel, gbc, "Área:", txtArea, row++);
        addFormField(formPanel, gbc, "Descripción Área:", txtDescArea, row++);
        addFormField(formPanel, gbc, "Salario por Hora:", txtSalHora, row++);
        addFormField(formPanel, gbc, "Horas por Mes:", txtHoras, row++);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar Profesor");
        JButton btnEliminar = new JButton("Eliminar Profesor");
        
        btnAgregar.addActionListener(e -> agregarProfesor());
        btnEliminar.addActionListener(e -> eliminarProfesor());
        
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEliminar);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // Tabla de profesores
        String[] columnas = {"Nombre", "Cédula", "Área", "Salario/Hora", "Horas/Mes", "Pago", "Prestaciones"};
        modeloTablaP = new DefaultTableModel(columnas, 0);
        tablaP = new JTable(modeloTablaP);
        JScrollPane scrollPane = new JScrollPane(tablaP);
        scrollPane.setPreferredSize(new Dimension(800, 200));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private Component buildTabEstudiantes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear campos de texto
        txtNombreE = new JTextField(15);
        txtDirE = new JTextField(15);
        txtTelE = new JTextField(15);
        txtNacE = new JTextField(15);
        txtCodigo = new JTextField(15);
        txtGrado = new JTextField(15);
        txtAcudiente = new JTextField(15);

        // Agregar componentes al formulario
        int row = 0;
        addFormField(formPanel, gbc, "Nombre:", txtNombreE, row++);
        addFormField(formPanel, gbc, "Dirección:", txtDirE, row++);
        addFormField(formPanel, gbc, "Teléfono:", txtTelE, row++);
        addFormField(formPanel, gbc, "Fecha Nacimiento:", txtNacE, row++);
        addFormField(formPanel, gbc, "Código:", txtCodigo, row++);
        addFormField(formPanel, gbc, "Grado:", txtGrado, row++);
        addFormField(formPanel, gbc, "Acudiente:", txtAcudiente, row++);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar Estudiante");
        JButton btnEliminar = new JButton("Eliminar Estudiante");
        
        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEliminar);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // Tabla de estudiantes
        String[] columnas = {"Nombre", "Código", "Grado", "Acudiente", "Teléfono"};
        modeloTablaE = new DefaultTableModel(columnas, 0);
        tablaE = new JTable(modeloTablaE);
        JScrollPane scrollPane = new JScrollPane(tablaE);
        scrollPane.setPreferredSize(new Dimension(800, 200));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private Component buildTabReportes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaReporte);
        
        JButton btnActualizar = new JButton("Actualizar Reportes");
        btnActualizar.addActionListener(e -> actualizarReportes());
        
        panel.add(btnActualizar, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String label, JTextField field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);
        
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    // ─────────────────────────────────────────────────────────────
    // ACCIONES (EVENTOS)
    // ─────────────────────────────────────────────────────────────

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
    // ACTUALIZACIÓN DE TABLAS Y REPORTES
    // ─────────────────────────────────────────────────────────────

    private void actualizarTablas() {
        if (modeloTablaP != null) {
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
        }

        if (modeloTablaE != null) {
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

        actualizarReportes();
    }

    private void actualizarReportes() {
        if (areaReporte != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("=== REPORTE DEL SISTEMA ===\n\n");
            
            sb.append("PROFESORES:\n");
            sb.append("-----------\n");
            for (Profesor p : colegio.getProfesores()) {
                sb.append(p.toString()).append("\n");
                sb.append("Pago: $").append(String.format("%,.2f", p.calcularPago())).append("\n");
                sb.append("Prestaciones: $").append(String.format("%,.2f", p.calcularPrestaciones())).append("\n");
                sb.append("------------------------\n");
            }
            
            sb.append("\nESTUDIANTES:\n");
            sb.append("------------\n");
            for (Estudiante e : colegio.getEstudiantes()) {
                sb.append(e.toString()).append("\n");
                sb.append("------------------------\n");
            }
            
            sb.append("\nTOTALES:\n");
            sb.append("--------\n");
            sb.append("Total Profesores: ").append(colegio.getProfesores().size()).append("\n");
            sb.append("Total Estudiantes: ").append(colegio.getEstudiantes().size()).append("\n");
            
            areaReporte.setText(sb.toString());
        }
    }

    // ─────────────────────────────────────────────────────────────
    // DATOS DE DEMOSTRACIÓN
    // ─────────────────────────────────────────────────────────────

    private void cargarDatosDemostracion() {
        // Agregar algunos profesores de ejemplo
        Area matematica = new Area("Matemáticas", "Ciencias exactas");
        Profesor prof1 = new Profesor("Juan Pérez", "Calle 1", "1234567", "15/03/1980", "1001", matematica, 50000, 160);
        colegio.agregarProfesor(prof1);

        Area lenguaje = new Area("Lenguaje", "Comunicación");
        Profesor prof2 = new Profesor("María Gómez", "Calle 2", "7654321", "22/07/1985", "1002", lenguaje, 45000, 160);
        colegio.agregarProfesor(prof2);

        // Agregar algunos estudiantes de ejemplo
        Estudiante est1 = new Estudiante();
        est1.setNombre("Carlos López");
        est1.setDireccion("Carrera 1");
        est1.setTelefono("1112223");
        est1.setFechaNacimiento("10/05/2010");
        est1.setCodigo("E001");
        est1.setGrado("10°");
        est1.setAcudiente("Ana López");
        colegio.agregarEstudiante(est1);

        Estudiante est2 = new Estudiante();
        est2.setNombre("Laura Martínez");
        est2.setDireccion("Calle 5");
        est2.setTelefono("4445556");
        est2.setFechaNacimiento("25/11/2011");
        est2.setCodigo("E002");
        est2.setGrado("9°");
        est2.setAcudiente("Pedro Martínez");
        colegio.agregarEstudiante(est2);
    }

    private void limpiarFormProfesor() {
        txtNombreP.setText("");
        txtDirP.setText("");
        txtTelP.setText("");
        txtNacP.setText("");
        txtCedula.setText("");
        txtArea.setText("");
        txtDescArea.setText("");
        txtSalHora.setText("");
        txtHoras.setText("");
    }

    private void limpiarFormEstudiante() {
        txtNombreE.setText("");
        txtDirE.setText("");
        txtTelE.setText("");
        txtNacE.setText("");
        txtCodigo.setText("");
        txtGrado.setText("");
        txtAcudiente.setText("");
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new MainFrame().setVisible(true);
        });
    }
}