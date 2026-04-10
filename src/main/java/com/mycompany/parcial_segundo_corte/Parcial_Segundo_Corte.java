package com.mycompany.parcial_segundo_corte;

import vista.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Parcial_Segundo_Corte {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new MainFrame().setVisible(true);
        });
    }
}