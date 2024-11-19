package com.andina.trading.util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.andina.trading.model.UsuarioEmpresa;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class GeneradorReportes {

    public byte[] exportarPdf(List<UsuarioEmpresa> list) throws JRException, FileNotFoundException {
    	return JasperExportManager.exportReportToPdf(conseguirReporte(list));
    }

    private JasperPrint conseguirReporte(List<UsuarioEmpresa> list) throws FileNotFoundException, JRException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuarioEmpresaData", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:usuarioEmpresa.jrxml")
                        .getAbsolutePath()), params, new JRBeanCollectionDataSource(list));

        return report;
    }
}
