package com.creando.serviceImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.creando.constantes.Constantes;
import com.creando.dap.AfiliadoDao;
import com.creando.dominio.Afiliado;
import com.creando.dto.AfiliadoDto;
import com.creando.excepciones.Excepciones;
import com.creando.logica.Calculos;
import com.creando.service.AfiliadoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class AfiliadoServiceImp implements AfiliadoService {

	private final AfiliadoDao dao;
	private final Calculos calculo;
	
	
	private final ModelMapper modelMapper = new ModelMapper();


	@Override
	public AfiliadoDto registro(Afiliado afiliado) throws Excepciones {

		if (Boolean.TRUE.equals(calculo.calculoRango(afiliado.getMontoMaximo(), afiliado.getMontoMinimo()))) {

			afiliado = dao.save(afiliado);
			ModelMapper model = new ModelMapper();
			model.validate();
			log.info("{}", "usuario creado");
			return model.map(afiliado, AfiliadoDto.class);
		} else {
			throw new Excepciones("OOPS algo no ha salido bien " + Constantes.RANGOS_NO_PERMITIDOS);
		}
	}
    
	@SuppressWarnings("unused")
	@Override
	public List<Afiliado> listaAfiliado() throws Excepciones {
		List<Afiliado> listaAfiliados = dao.findAll();
		List<AfiliadoDto> dto = new ArrayList<>();
		if(listaAfiliados.isEmpty()) {
			throw new Excepciones(Constantes.LISTAS_VACIAS);
		}else {
		dto = listaAfiliados.stream()
			    .map(afiliado -> modelMapper.map(afiliado, AfiliadoDto.class))
			    .collect(Collectors.toList());
		 Collections.emptyList();
	}
		return listaAfiliados;
		}

	@Override
	public AfiliadoDto buscarPorId(Long id) {
	  return dao.findById(id).map(afiliado-> {
		 ModelMapper maper = new ModelMapper();
		 return maper.map(afiliado, AfiliadoDto.class);
	  }).orElseThrow(()-> new Excepciones(Constantes.VALORES_NO_ENCONTRADOS));
	
	
	}

	@Override
	public void eliminar(Long id) {
		 Afiliado af = dao.findById(id).orElse(null);
		 dao.delete(af);
	}

	@Override
	public Afiliado buscarPorNumeroDeIdentificacion(int numeroDocumento) throws Excepciones {
		Afiliado afil = dao.buscarPorIdentificacion(numeroDocumento);
		if (afil != null) {
			return afil;
		} else {
			throw new Excepciones(Constantes.DC_NO_REGISTRA);
		}
	}

	@Override
	public AfiliadoDto actualizar(@RequestBody Afiliado afiliado, @PathVariable Integer numeroDocumento) {
		Afiliado afiliados = dao.buscarPorIdentificacion(numeroDocumento);

		afiliados.getId();
		if(afiliado != null) {
		afiliados.setNombre(afiliado.getNombre());
		afiliados.setTipoDocumento(afiliado.getTipoDocumento());
		afiliados.setNumeroDocumento(afiliado.getNumeroDocumento());
		afiliados.setApellido(afiliado.getApellido());
		afiliados.setDepartamento(afiliado.getDepartamento());
		afiliados.setCiudad(afiliado.getCiudad());
		afiliados.setEmail(afiliado.getEmail());
		afiliados.setTelefono(afiliado.getTelefono());
		afiliados.setMontoMaximo(afiliado.getMontoMaximo());
		afiliados.setMontoMinimo(afiliado.getMontoMinimo());
		dao.save(afiliados);
		ModelMapper model = new ModelMapper();
		return model.map(afiliados, AfiliadoDto.class);
		}
		return null;
	
	}

	@Override
	public Afiliado actualizarMontos(int numeroDocumento, Afiliado af) {
		Afiliado afil = dao.buscarPorIdentificacion(numeroDocumento);
		afil.setMontoMaximo(af.getMontoMaximo());
		afil.setMontoMinimo(af.getMontoMinimo());
		dao.save(afil);
		return afil;
	}

}
