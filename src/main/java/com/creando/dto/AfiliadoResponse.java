package com.creando.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AfiliadoResponse {

	
	@JsonInclude(value = Include.NON_NULL)
	private String code;
	@JsonInclude(value = Include.NON_NULL)
	private AfiliadoDto data;
	@JsonInclude(value = Include.NON_NULL)
	private Object errores;

}
