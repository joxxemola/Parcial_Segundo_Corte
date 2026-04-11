package com.mycompany.parcial_segundo_corte;

import vista.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Parcial_Segundo_Corte {

    /**
     * Método principal que inicia la aplicación.
     *
     * <p>Configura el entorno gráfico y lanza la ventana principal.</p>
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {

        // Ejecutar la interfaz en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                // Aplicar el estilo visual del sistema operativo
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
                // En caso de error, se usa el estilo por defecto
            }

            // Crear y mostrar la ventana principal
            new MainFrame().setVisible(true);
        });
    }
}