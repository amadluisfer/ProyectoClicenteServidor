package com.mycompany.fidecompro;
//Estudiante: Luis Fernando Amador Ríos

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// hereda de JDialog, muestra la factura emitida y permite guardarla en un archivo .txt
public class VentanaVistaFactura extends JDialog {

    private Factura factura;

    // recibe la ventana de factura y la factura que se acaba de emitir
    public VentanaVistaFactura(JFrame padre, Factura factura) {
        // el true hace que no se pueda usar la ventana de atrás hasta cerrar esta
        super(padre, "Factura " + factura.getNumeroFormateado(), true);
        this.factura = factura;
        setSize(600, 520);
        setLocationRelativeTo(padre);
        setLayout(null);
        setResizable(false);

        // área de texto con la factura, no se puede editar
        JTextArea area = new JTextArea(factura.mostrarInformacion());
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(10, 10, 565, 410);
        
        add(scroll);

        // botones para guardar el archivo y cerrar
        JButton btnGuardar = new JButton("Guardar archivo (.txt)");
        btnGuardar.setBounds(300, 435, 175, 30);
        add(btnGuardar);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(485, 435, 90, 30);
        add(btnCerrar);

        btnGuardar.addActionListener(e -> guardarArchivo());
        btnCerrar.addActionListener(e -> dispose());
    }

    // guarda la factura en un archivo .txt en la carpeta que se elija
    private void guardarArchivo() {
        // ventana para escoger solo la carpeta, el archivo se llama con el número de la factura
        JFileChooser selector = new JFileChooser();
        
        selector.setFileSelectionMode(JFileChooser. DIRECTORIES_ONLY);
        
        selector.setDialogTitle("Seleccione la carpeta donde guardar la factura");
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        // se crea el archivo y se muestra dónde quedó
        File carpeta = selector.getSelectedFile();
        try {
            String ruta = factura.generarArchivo(carpeta.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Archivo guardado en " + ruta + ".");
        } catch (IOException ex) {
            // si no se pudo guardar se avisa al usuario , la factura sigue emitida
            JOptionPane.showMessageDialog(this, "No se pudo guardar el archivo de la factura.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
