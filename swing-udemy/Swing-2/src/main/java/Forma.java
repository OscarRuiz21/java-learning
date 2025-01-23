import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.*;

public class Forma extends JFrame{
    private JPanel panel1;

    public Forma(){
        inicializarForma();
    }

    private void inicializarForma(){
        // Inicializar el panel
        panel1 = new JPanel();
        panel1.add(new JLabel("¡Hola, Mundo!")); // Agregar un componente al panel

        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null); //centra la ventana
    }

    public static void main(String[] args) {
        // Crear el frame
        FlatDarculaLaf.setup();  // Cambiar a modo obscuro
        Forma forma = new Forma();
        forma.setVisible(true);
    }
}

