package gm.zona_fit;

import com.sun.tools.jconsole.JConsoleContext;
import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.dialect.hint.IndexQueryHintHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.ref.Cleaner;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {


	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger =
			LoggerFactory.getLogger(ZonaFitApplication.class);
	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		// Levanta la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion Finalizada!");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp() {

		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl + "");
		}
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion) {
		var salir  = false;
		switch(opcion){
			case 1 -> {
				logger.info(nl+ "--- Listado de Clientes ---" + nl);
				List<Cliente> clientes = clienteServicio.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString() + nl));
			}
			case 2 -> {
				logger.info(nl+ "--- Buscar Cliente por Id ---" + nl);
				logger.info(nl+ "Id Cliente a buscar: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null)
					logger.info("Cliente encontrado " + cliente + nl);
				else
					logger.info("Cliente NO encontrado " + cliente + nl);
			}
			case 3 -> {
				logger.info(nl+ "--- Agregar Estudiante ---" + nl);
				logger.info(nl+ "Nombre: ");
				var nombre = consola.nextLine();
				logger.info(nl+ "Apellido: ");
				var apellido = consola.nextLine();
				logger.info(nl+ "Membresia: ");
				var membresia = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteServicio.guardarCliente(cliente);
				logger.info("Cliente agregado " + cliente + nl);
			}
			case 4 -> {
				logger.info(nl+ "--- Modificar Cliente ---" + nl);
				logger.info(nl+ "Id Cliente: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null) {
					logger.info(nl + "Nombre: ");
					var nombre = consola.nextLine();
					logger.info(nl + "Apellido: ");
					var apellido = consola.nextLine();
					logger.info(nl + "Membresia: ");
					var membresia = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
					clienteServicio.guardarCliente(cliente);
					logger.info("Cliente modificado " + cliente + nl);
				}
				else
					logger.info("Cliente no encontrado " + cliente + nl);
			}
			case 5 -> {
				logger.info(nl+ "--- Eliminar Cliente ---" + nl);
				logger.info(nl+ "Id Cliente: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null) {
					clienteServicio.eliminarCliente(cliente);
					logger.info("Cliente eliminado " + cliente + nl);
				}
				else
					logger.info("Cliente no encontrado " + cliente + nl);
			}
			case 6 -> {
				logger.info(nl + "--- Salir de la Aplicación ---" + nl);
				salir = true;
			}
			default -> logger.info("Opción inválida. Por favor, elige una opción del 1 al 6." + nl);

		}
		return salir;
	}

	private int mostrarMenu(Scanner consola) {
		logger.info(nl + "*** Aplicacion Zona Fit (GYM) ***" + nl);
		logger.info("""
				1. Listar Clientes
				2. Buscar Clientes
				3. Agregar Cliente
				4. Modificar Cliente
				5. Eliminar Cliente
				6. Salir
				Elige una opcion: \s""");
		return Integer.parseInt(consola.nextLine());
	}
}
