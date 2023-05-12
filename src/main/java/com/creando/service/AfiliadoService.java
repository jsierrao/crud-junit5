package com.creando.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.creando.dominio.Afiliado;
import com.creando.dto.AfiliadoDto;

public interface AfiliadoService {
	
	public AfiliadoDto registro(Afiliado afiliado);
	
	public List<Afiliado> listaAfiliado();
	
	public AfiliadoDto buscarPorId(Long id);
	
	public Afiliado buscarPorNumeroDeIdentificacion(int numeroDocumento);
	
	public Afiliado actualizarMontos(int numeroDocumento,Afiliado afil);
	
	public void eliminar(Long id);
	
	public AfiliadoDto actualizar(@RequestBody Afiliado afiliado,
			@PathVariable Integer numeroDocumento);

	

}
