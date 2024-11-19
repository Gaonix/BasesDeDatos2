package com.andina.trading.service;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andina.trading.repository.UsuarioEmpresasRepository;
import com.andina.trading.util.GeneradorReportes;

import net.sf.jasperreports.engine.JRException;

@Service
public class UsuarioEmpresaService {
	
	@Autowired
    private UsuarioEmpresasRepository usuarioEmpresaRepository;
	
	@Autowired
    private GeneradorReportes generador;
	
	 public byte[] exportarPdf() throws JRException, FileNotFoundException {
	        return generador.exportarPdf(usuarioEmpresaRepository.findAll());
	    }

}
