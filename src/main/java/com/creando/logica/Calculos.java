package com.creando.logica;

import org.springframework.context.annotation.Configuration;

import com.creando.dap.RangosDao;
import com.creando.dominio.Rangos;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Calculos {

	private static final Boolean respuesta = null;
	private final RangosDao rangos;

	public Boolean calculoRango(int montoMax, int montoMin) {

		Rangos rango = rangos.findById(1L).orElse(null);
		if (rango != null) {
			if (montoMax > rango.getMontoMax() && montoMin > rango.getMontoMin()) {
				return false;
			} else if (montoMax <= rango.getMontoMax() && montoMin <= rango.getMontoMin()) {
				return true;
			}

		}
		return respuesta;

	}
}