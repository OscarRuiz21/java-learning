package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        //Creamos un objeto de la clase ClienteDao
        IClienteDAO clienteDAO = new ClienteDAO();
        while(!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDAO);
            }catch(Exception e){
                System.out.println("Error al ejecutar las opciones: " + e.getMessage());
            }
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Zona Fit (GYM)
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elige una opcion:
                
                """);
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch(opcion){
            case 1 -> { // 1. Listar Clientes
                System.out.println("--- Listado de Clientes ---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> { // 2. Buscar cliente por id
                System.out.println("Introduce el id del Cliente a buscar:");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente NO encontrado: " + cliente);
            }
            case 3 -> { // 3. Agregar cliente
                System.out.println("--- Agregar cliente ---");
                System.out.println("Nombre:");
                var nombre = consola.nextLine();
                System.out.println("Apellido:");
                var apellido = consola.nextLine();
                System.out.println("Membresia:");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if (agregado)
                    System.out.println("Cliente agregado: " + cliente);
                else
                    System.out.println("Cliente NO agregado: " + cliente);
            }
            case 4 -> { // 4. Modificar cliente
                System.out.println("--- Modificar cliente ---");
                System.out.println("ID Cliente a modificar:");
                var id = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre:");
                var nombre = consola.nextLine();
                System.out.println("Apellido:");
                var apellido = consola.nextLine();
                System.out.println("Membresia:");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(id, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if (modificado)
                    System.out.println("Cliente modificado: " + cliente);
                else
                    System.out.println("Cliente NO modificado: " + cliente);
            }
            case 5 -> { // 5. Eliminar cliente
                System.out.println("--- Eliminar cliente ---");
                System.out.println("ID Cliente a eliminar:");
                var id = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(id);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if (eliminado)
                    System.out.println("Cliente modificado: " + cliente);
                else
                    System.out.println("Cliente NO modificado: " + cliente);
            }
            case 6 -> { // 5. Eliminar cliente
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida" + opcion);
        }
        return salir;
    }
}
