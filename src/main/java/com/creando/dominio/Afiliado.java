package com.creando.dominio;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Afiliado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;

	private String tipoDocumento;

	private Integer numeroDocumento;

	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = true)
	private String telefono;
	@Column(nullable = false)
	private String departamento;
	@Column(nullable = false)
	private String ciudad;
	@Column(nullable = false)
	@JsonInclude(value = Include.NON_NULL)
	private int montoMaximo;
	@Column(nullable = false)
	@JsonInclude(value = Include.NON_NULL)
	private int montoMinimo;

}
