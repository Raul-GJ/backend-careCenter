package salud;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import salud.servicio.IServicioEspecialistas;

public class TestApp {

	public static void main(String[] args) throws Exception {
		
		ConfigurableApplicationContext contexto = SpringApplication.run(TfgApplication.class, args);
		
		System.out.println("Comenzando aplicación");
		
		IServicioEspecialistas servicioEspecialistas = contexto.getBean(IServicioEspecialistas.class);
		
		Collection<String> plantillas = new LinkedList<String>();
		plantillas.add("67daba953b81f8437e910d41");
		
		servicioEspecialistas.agregarPlantillas("67e3e13e39eebc4e6bdaf121", plantillas);
		
		System.out.println("Adiós!");
		
		contexto.close();

	}

}
