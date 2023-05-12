package com.creando.dap;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creando.dominio.Usuarios;


public interface UsuarioDao extends JpaRepository<Usuarios, Long>{

}
