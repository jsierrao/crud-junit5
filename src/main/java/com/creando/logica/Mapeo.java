package com.creando.logica;
import org.springframework.context.annotation.Configuration;
import com.creando.dominio.Afiliado;


@Configuration
public class Mapeo {
	 
	
	public Afiliado mapear(Afiliado afil){
		Afiliado af = new Afiliado();
		af.setId(afil.getId());
		af.setNombre(afil.getNombre());
		af.setApellido(afil.getApellido());
		af.setDepartamento(afil.getDepartamento());
		af.setCiudad(afil.getCiudad());
		af.setEmail(afil.getEmail());
		af.setNumeroDocumento(afil.getNumeroDocumento());
		af.setTipoDocumento(afil.getTipoDocumento());
		af.setMontoMaximo(afil.getMontoMaximo());
		af.setMontoMinimo(afil.getMontoMinimo());
		return af;
	}
	
	
	
	
	

}
