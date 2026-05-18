package com.aguape.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperService {

    public byte[] gerarCertidaoPositiva() {
        try {
            // 1. Carrega o arquivo da pasta certa
            InputStream reportStream = getClass().getResourceAsStream("/reports/relatorio_principal.jrxml");

            if (reportStream == null) {
                // Esse erro vai te avisar no console se ele ainda não achar
                throw new FileNotFoundException("Arquivo relatorio_principal.jrxml não encontrado em resources/report/");
            }

            // 2. Compila o relatório
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // 3. Dados para o teste (os mesmos do PDF que você mandou)
            Map<String, Object> registro = new HashMap<>();
            registro.put("nomeRazao", "IRANI DE JESUS DO SANTOS");
            registro.put("documento", "056.878.915-60");
            registro.put("logradouro", "R. Z, 56");
            registro.put("bairro", "BAIUCA");
            registro.put("codigoAutenticidade", "B6D0E875");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(registro));

            // 4. Parâmetros
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("municipio", "SECRETARIA MUNICIPAL DE FINANÇAS");

            // 5. Gera o PDF
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}