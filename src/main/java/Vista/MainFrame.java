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

        // Panel de formulario con GridLayout (filas x columnas)
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

        // ========== FILA 1 ==========
        // Columna 1: Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtNombreP, gbc);

        // Columna 2: Dirección
        gbc.gridx = 2;
        formPanel.add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 3;
        formPanel.add(txtDirP, gbc);

        // Columna 3: Teléfono
        gbc.gridx = 4;
        formPanel.add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 5;
        formPanel.add(txtTelP, gbc);

        // ========== FILA 2 ==========
        // Columna 1: Fecha Nacimiento
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Fecha Nac.:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtNacP, gbc);

        // Columna 2: Cédula
        gbc.gridx = 2;
        formPanel.add(new JLabel("Cédula:"), gbc);

        gbc.gridx = 3;
        formPanel.add(txtCedula, gbc);

        // Columna 3: Área
        gbc.gridx = 4;
        formPanel.add(new JLabel("Área:"), gbc);

        gbc.gridx = 5;
        formPanel.add(txtArea, gbc);

        // ========== FILA 3 ==========
        // Columna 1: Descripción Área
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtDescArea, gbc);

        // Columna 2: Salario por Hora
        gbc.gridx = 2;
        formPanel.add(new JLabel("Salario/Hora:"), gbc);

        gbc.gridx = 3;
        formPanel.add(txtSalHora, gbc);

        // Columna 3: Horas por Mes
        gbc.gridx = 4;
        formPanel.add(new JLabel("Horas/Mes:"), gbc);

        gbc.gridx = 5;
        formPanel.add(txtHoras, gbc);

        // ========== FILA 4: Botones ==========
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnAgregar = new JButton("Agregar Profesor");
        JButton btnEliminar = new JButton("Eliminar Profesor");

        // Estilos para botones
        btnAgregar.setBackground(new Color(46, 204, 113));
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setPreferredSize(new Dimension(180, 35));

        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new Dimension(180, 35));

        btnAgregar.addActionListener(e -> agregarProfesor());
        btnEliminar.addActionListener(e -> eliminarProfesor());

        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEliminar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 6;  // Ocupa todas las columnas
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

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear campos
        txtNombreE = new JTextField(15);
        txtDirE = new JTextField(15);
        txtTelE = new JTextField(15);
        txtNacE = new JTextField(15);
        txtCodigo = new JTextField(15);
        txtGrado = new JTextField(15);
        txtAcudiente = new JTextField(15);

        // ========== FILA 1 ==========
        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNombreE, gbc);

        // Dirección
        gbc.gridx = 2;
        formPanel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 3;
        formPanel.add(txtDirE, gbc);

        // Teléfono
        gbc.gridx = 4;
        formPanel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 5;
        formPanel.add(txtTelE, gbc);

        // ========== FILA 2 ==========
        // Fecha Nacimiento
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Fecha Nac.:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNacE, gbc);

        // Código
        gbc.gridx = 2;
        formPanel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 3;
        formPanel.add(txtCodigo, gbc);

        // Grado
        gbc.gridx = 4;
        formPanel.add(new JLabel("Grado:"), gbc);
        gbc.gridx = 5;
        formPanel.add(txtGrado, gbc);

        // ========== FILA 3 ==========
        // Acudiente (ocupa más espacio)
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Acudiente:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 5;  // Ocupa desde columna 1 hasta 5
        formPanel.add(txtAcudiente, gbc);
        gbc.gridwidth = 1;  // Restaurar

        // ========== FILA 4: Botones ==========
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnAgregar = new JButton("Agregar Estudiante");
        JButton btnEliminar = new JButton("Eliminar Estudiante");

        btnAgregar.setBackground(new Color(52, 152, 219));
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAgregar.setPreferredSize(new Dimension(180, 35));

        btnEliminar.setBackground(new Color(241, 196, 15));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminar.setPreferredSize(new Dimension(180, 35));

        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());

        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEliminar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 6;
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