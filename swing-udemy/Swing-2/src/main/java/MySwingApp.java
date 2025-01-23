import javax.swing.*;

public class MySwingApp {
    public static void main(String[] args) {
        // Crear el frame
        JFrame frame = new JFrame("Mi Aplicación Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Crear un botón
        JButton button = new JButton("Haz clic aquí");
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "¡Hola, Swing!"));

        // Agregar el botón al frame
        frame.getContentPane().add(button);

        // Hacer visible el frame
        frame.setVisible(true);
    }
}