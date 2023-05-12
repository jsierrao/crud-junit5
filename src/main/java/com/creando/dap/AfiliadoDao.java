package com.creando.dap;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creando.dominio.Afiliado;

public interface AfiliadoDao extends JpaRepository<Afiliado, Long> {

	Optional<Afiliado> findByEmail(String email);
	
	@Query(value = "select * from afiliado where numero_documento= :numeroDocumento", nativeQuery = true)
	Afiliado buscarPorIdentificacion(@Param("numeroDocumento") Integer numeroDocumento);
	
	@Query(value ="select a.nombre,a.apellido,a.monto_maximo, a.monto_minimo from afiliado a where numero_docuemnto= :numeroDocumento",nativeQuery = true)
	Afiliado actualizarMontos(@Param("numeroDocumento")Integer numeroDocumentoInteger);

	

}
