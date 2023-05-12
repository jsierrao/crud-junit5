package com.creando.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creando.dominio.Afiliado;
import com.creando.dto.AfiliadoDto;
import com.creando.dto.AfiliadoResponse;
import com.creando.service.AfiliadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AfiliadoController {

	private final AfiliadoService service;

	@PostMapping("/registro")
	public ResponseEntity<AfiliadoResponse> registrarAfiliado(@Validated @RequestBody Afiliado afiliado,
			BindingResult result) {

		Map<String, Object> afiliados = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(error -> "el campo" + error.getField() + " ' " + error.getDefaultMessage())
					.collect(Collectors.toList());
			afiliados.put("errores", errores);
		}
		try {
 
			AfiliadoDto nuevo = service.registro(afiliado);
			AfiliadoResponse rsp = new AfiliadoResponse();
			rsp.setCode(String.valueOf(HttpStatus.CREATED));
			rsp.setData(nuevo);
			return ResponseEntity.status(HttpStatus.CREATED).body(rsp);
 
		} catch (DataAccessException e) {
			AfiliadoResponse rsp = new AfiliadoResponse();
			rsp.setCode(HttpStatus.BAD_REQUEST.name());
			return new ResponseEntity<>(rsp, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/registro")
	public ResponseEntity<List<Afiliado>> listaAfiliaciones() {
		List<Afiliado> list = service.listaAfiliado();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/registro/{id}")
	public ResponseEntity<AfiliadoDto> obtenerPorId(@PathVariable Long id) {
		AfiliadoDto afiliado = service.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(afiliado);

	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminarAfiliado(@PathVariable Long id) {

		service.eliminar(id);
	}

	@PutMapping("/actualizar/{numeroDocumento}")
	public ResponseEntity<AfiliadoDto> actualizar(@RequestBody Afiliado afiliado,
			@PathVariable Integer numeroDocumento) {
		AfiliadoDto afil = service.actualizar(afiliado, numeroDocumento);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(afil);
	}

	@GetMapping("/buscaIdentificacion")
	public ResponseEntity<Afiliado> buscarPorCedula(
			@RequestParam(value = "numeroDocumento", required = true) Integer numeroDocumento) {
		Afiliado afil = service.buscarPorNumeroDeIdentificacion(numeroDocumento);
		return ResponseEntity.status(HttpStatus.OK).body(afil);

	}
	
	@PutMapping("/actualizarmontos/{numeroDocumento}")
	public Afiliado actualizarMontos(@PathVariable Integer numeroDocumento,@RequestBody Afiliado af) {
		return service.actualizarMontos(numeroDocumento, af);
	}

}
