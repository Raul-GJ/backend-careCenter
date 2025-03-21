package salud;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import salud.modelo.PlantillaFormulario;
import salud.modelo.Usuario;
import salud.modelo.encuesta.ReglaEnum;
import salud.modelo.encuesta.ReglaRango;
import salud.modelo.encuesta.PreguntaEncuesta;
import salud.servicio.IServicioFormulariosPlantilla;
import salud.servicio.IServicioUsuarios;

public class TestApp {

	public static void main(String[] args) throws Exception {
		
		ConfigurableApplicationContext contexto = SpringApplication.run(TfgApplication.class, args);
		
		System.out.println("Comenzando aplicación");
		
		IServicioUsuarios servicioUsuarios = contexto.getBean(IServicioUsuarios.class);
		
		String idUsr = servicioUsuarios.altaUsuario("Pedro", "abc", "def", "email1@gmail.com", "123456789");
		
		Usuario usuario = servicioUsuarios.obtenerUsuario(idUsr);
		
		System.out.println(usuario.getNombre() + ", " + usuario.getId());
		
		System.out.println("Prueba de formularios");
		
		IServicioFormulariosPlantilla servicioFormulariosPlantilla = contexto.getBean(IServicioFormulariosPlantilla.class);
		
		String idForm = servicioFormulariosPlantilla.altaFormulario("Formulario 1", "Descripción formulario 1");
		
		PreguntaEncuesta pregunta1 = new PreguntaEncuesta(idForm, new ReglaRango(100, 200));
		
		Collection<String> enumPregunta2 = new LinkedList<String>();
		enumPregunta2.add("opción 1");
		enumPregunta2.add("opción 2");
		
		PreguntaEncuesta pregunta2 = new PreguntaEncuesta(idForm, new ReglaEnum(enumPregunta2));
		
		servicioFormulariosPlantilla.agregarPregunta(idForm, pregunta1);
		servicioFormulariosPlantilla.agregarPregunta(idForm, pregunta2);
		
		PlantillaFormulario form = servicioFormulariosPlantilla.obtenerFormulario(idForm);
		System.out.println(form);
		
		System.out.println("Adiós!");
		
		contexto.close();

	}

}
