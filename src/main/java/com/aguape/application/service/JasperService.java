package com.aguape.application.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperService {

    public byte[] gerarRelatorio(List<User> usuarios) throws Exception {
        InputStream input = getClass().getResourceAsStream("/reports/relatorio_exemplo.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(input);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("titulo", "Relatório de Usuários SEAD");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usuarios);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
