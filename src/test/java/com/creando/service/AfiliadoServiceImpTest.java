package com.creando.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.creando.constantes.Constantes;
import com.creando.dap.AfiliadoDao;
import com.creando.dap.RangosDao;
import com.creando.dominio.Afiliado;
import com.creando.dominio.Rangos;
import com.creando.dto.AfiliadoDto;
import com.creando.excepciones.Excepciones;
import com.creando.logica.Calculos;
import com.creando.serviceImp.AfiliadoServiceImp;

@ExtendWith(MockitoExtension.class)
public class AfiliadoServiceImpTest {

	@Mock
	AfiliadoDao dao;

	@Mock
	Calculos calculo;
	
	@Mock
	RangosDao rangos;

	@InjectMocks
	AfiliadoServiceImp service;
	
	
	@InjectMocks
	Calculos cal;
	
	
	
	@Test
	void calculoRangoTest(){
		when(rangos.findById(1L)).thenReturn(rango());
		Boolean r = cal.calculoRango(1234, 123);
		assertTrue(r);
		
	}
	
	@Test
	void calculoRangoTestFalla(){
		when(rangos.findById(1L)).thenReturn(rango());
		Boolean r = cal.calculoRango(123456, 12345);
		assertFalse(r);
		
	}
	
	Optional<Rangos> rango() {
		Rangos r = new Rangos();
		r.setId(1L);
		r.setMontoMax(12345);
		r.setMontoMin(1234);
		return Optional.of(r);
	}
	
	
	

	@Test
	 void registroTest() {
		 
		 
		 when(calculo.calculoRango(anyInt(), anyInt())).thenReturn(true);
		 when(dao.save(any(Afiliado.class))).thenReturn(registro());
		 
		 AfiliadoDto dto = service.registro(registro());
		 assertNotNull(dto);
		 verify(dao, atLeastOnce()).save(any(Afiliado.class));
		 
	 }

	@Test
	 void registroFallidoTest() {
		 when(calculo.calculoRango(anyInt(), anyInt())).thenReturn(false);
		 assertThrows(Excepciones.class,()-> service.registro(registro()));
		 
	 }

	@Test
	void listaAfiliadoTest() {
		
		when(dao.findAll()).thenReturn(lista());
		List<Afiliado> dto =  service.listaAfiliado();
		
		assertNotNull(dto);
		verify(dao, atLeastOnce()).findAll();
		
	}

	@Test
	void listaAfiliadoFallo() {
		when(dao.findAll()).thenReturn(listaVacia());
		assertThrows(Excepciones.class, ()-> service.listaAfiliado());
		
	}

	@Test
	void buscarPorIdTest() {
		when(dao.findById(1L)).thenReturn(buscarPorIds());
		AfiliadoDto af = service.buscarPorId(1L);
		
		assertNotNull(af);
		verify(dao, atLeastOnce()).findById(1L);
	}


	@Test
	void buscarPorDocumentoTest() {
		when(dao.buscarPorIdentificacion(12345)).thenReturn(buscaDoc());
		Afiliado af = service.buscarPorNumeroDeIdentificacion(12345);
		
		assertNotNull(af);
		verify(dao, atLeastOnce()).buscarPorIdentificacion(12345);
	}
	
	
	@Test
	void actualizarTest() {
		when(dao.buscarPorIdentificacion(12345)).thenReturn(buscaDoc());
		//when(dao.save(any(Afiliado.class))).thenReturn(registro());
		AfiliadoDto afo = service.actualizar(actualizar(),12345);
		assertNotNull(afo);
		verify(dao, atLeastOnce()).buscarPorIdentificacion(12345);
		
	}
	
	@Test
	void eliminarTes() {
		when(dao.findById(2L)).thenReturn(buscarPorIds());
		service.eliminar(2L);
		assertTrue(true);
		verify(dao, atLeastOnce()).delete(any(Afiliado.class));
	}
	
	@Test
	void actualizarMontosTest() {
		when(dao.buscarPorIdentificacion(12345)).thenReturn(buscaDoc());
		Afiliado af = service.actualizarMontos(12345, actualizarValor());
		assertNotNull(af);
		
		
	}
	
	Afiliado actualizarValor() {
		Afiliado af = new Afiliado();
		af.setNumeroDocumento(12345);
		af.setMontoMaximo(123456);
		af.setMontoMinimo(12345);
		Afiliado afi = new Afiliado();
		afi.getNumeroDocumento();
		afi.setMontoMaximo(af.getMontoMaximo());
		afi.setMontoMinimo(af.getMontoMinimo());
		return afi;
	}

	Afiliado registro() {
		Afiliado af = new Afiliado();
		af.setNombre("julio");
		af.setMontoMaximo(245789);
		af.setMontoMinimo(1234);
		return af;
	}
	
	Afiliado actualizar() {
		Afiliado af = new Afiliado();
		af.setId(1L);
		af.setNombre("marco");
		af.setApellido("perez");
		AfiliadoDto dto = new AfiliadoDto();
		dto.setId(af.getId());
	    dto.setNombre(af.getNombre());
		dto.setApellido(af.getApellido());;
		return af;
		
	}
	

	List<Afiliado> lista() {
		List<Afiliado> afiliado = new ArrayList<>();
		Afiliado af = new Afiliado();
		af.setNombre("julio");
		af.setApellido("sierra");
		af.setCiudad("mlaqmbo");
		afiliado.add(af);
		return afiliado;

	}

	List<Afiliado> listaVacia() {
		List<Afiliado> afiliado = new ArrayList<>();
		if (afiliado.isEmpty()) {
			System.out.println("lista vacia");
		}
		return afiliado;

	}

	Optional<Afiliado> buscarPorIds() {
		Afiliado af = new Afiliado();
		af.setId(1L);
		return Optional.of(af);
	}

	Optional<Afiliado> buscarPorIdFallo() {
		Afiliado af = new Afiliado();
		return Optional.of(af);
	}

	Afiliado buscaDoc() {
		Afiliado af = new Afiliado();
		af.setId(1L);
		af.setNumeroDocumento(12345);
		af.setNombre("sebastian");
		af.setApellido("zarate");
		if(af != null) {
		return af;
		}else {
			throw new Excepciones(Constantes.DC_NO_REGISTRA);
		}
		
		
	}

}
