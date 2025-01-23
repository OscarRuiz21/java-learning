import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JPanel panelPrincipal;
    private JTextField usuarioTexto;
    private JPasswordField passwordTexto;
    private JButton enviarBoton;

    public LoginForm(){
        inicializarForma();
        enviarBoton.addActionListener(e -> validar());
    }

    private void inicializarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null); //centramos la ventana
    }

    private void validar(){
        // Leer Valores
        var usuario = this.usuarioTexto.getText();
        var password = new String(this.passwordTexto.getPassword());

        if("root".equals(usuario) && "admin".equals(password))
            mostrarMensaje("Datos correctos, bienvenido!");
        else if("root".equals(usuario))
            mostrarMensaje("Password Incorrecto, corregirlo!");
        else
            mostrarMensaje("Datos Incorrectos, corregirlos!");
    }

    private void mostrarMensaje(String  mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public static void main(String[] args) {
        //FlatLightLaf.setup();// claro
        FlatDarculaLaf.setup();
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}
