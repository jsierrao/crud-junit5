package com.creando.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.creando.dominio.Afiliado;
import com.creando.dto.AfiliadoDto;
import com.creando.dto.AfiliadoResponse;
import com.creando.service.AfiliadoService;

@ExtendWith(MockitoExtension.class)
public class AfiliadoControllerTest {

	@Mock
	AfiliadoService service;

	@InjectMocks
	AfiliadoController controller;

	@Test
	void registrarAfiliadoTest() {
		Afiliado afiliado = new Afiliado();
		BindingResult result = new BeanPropertyBindingResult(afiliado, "afiliado");

		when(service.registro(any(Afiliado.class))).thenReturn(registrarAfiliado(result));

		ResponseEntity<AfiliadoResponse> af = controller.registrarAfiliado(afiliado, result);
		assertNotNull(af);
	}

	AfiliadoDto registrarAfiliado(BindingResult result) {
		AfiliadoDto af = new AfiliadoDto();
		af.setId(1L);
		af.setNombre("julio");
		af.setApellido("sierra");
		return af;
	}

	@Test
	void listaAfiliacionesTest() {
		when(service.listaAfiliado()).thenReturn(ListaAfiliados());
		ResponseEntity<List<Afiliado>> af = controller.listaAfiliaciones();
		assertNotNull(af);
	}

	List<Afiliado> ListaAfiliados() {
		List<Afiliado> listas = new ArrayList<>();
		Afiliado af = new Afiliado();
		af.setNombre("julio");
		af.setApellido("apellido");
		listas.add(af);
		return listas;

	}

	@Test
	void obtenerPorIdTest() {
		
		when(service.buscarPorId(1L)).thenReturn(obtenerPorId());
		ResponseEntity<AfiliadoDto>af = controller.obtenerPorId(1L);
		assertNotNull(af);
		
	}

	AfiliadoDto obtenerPorId() {
		Afiliado af = new Afiliado();
		af.setId(1L);
		AfiliadoDto dto = new AfiliadoDto();
		dto.setId(af.getId());
		return dto;
	}

	@Test
	void eliminarAfiliadoTest() {
		controller.eliminarAfiliado(1L);
	}

	@Test
	void actualizarTest() {
		
		lenient().when(service.actualizar(afiliado(), 12345)).thenReturn(actualizardto());
		
		ResponseEntity<AfiliadoDto> dto = controller.actualizar(afiliado(), 12345);
		assertNotNull(dto);
	}

	@Test
	void buscarPorCedulaTest() {
	 
		when(service.buscarPorNumeroDeIdentificacion(12345)).thenReturn(obtenerPorDoc());
		ResponseEntity<Afiliado> rsp = controller.buscarPorCedula(12345);
		assertNotNull(rsp);
		
	}

	@Test
	void actualizarMontosTest() {
		lenient().when(service.actualizarMontos(12345, afiliado())).thenReturn(Monto());
		Afiliado rsp = controller.actualizarMontos(12345,afiliado());
		assertNull(rsp);
	}

	Afiliado afiliado() {
		Afiliado af = new Afiliado();
		af.setId(1L);
		af.setNombre("julio");
		af.setCiudad("sierra");
		return af;
	}

	Afiliado Monto() {
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

	Afiliado obtenerPorDoc() {
		Afiliado af = new Afiliado();
		af.setNombre("julio");
		af.setApellido("sierra");
		af.setNumeroDocumento(12345);
		return af;
	}

	AfiliadoDto actualizardto() {
		Afiliado af = new Afiliado();
		af.setId(1L);
		af.setNombre("marco");
		af.setApellido("perez");
		AfiliadoDto dto = new AfiliadoDto();
		dto.setId(af.getId());
		dto.setNombre(af.getNombre());
		dto.setApellido(af.getApellido());
		;
		return dto;

	}

}
