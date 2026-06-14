package com.aguape.service;

import com.aguape.dto.ResumoPainelDTO;
import com.aguape.dto.StatusFrotaDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperService {

    public byte[] gerarCertidaoPositiva() {
        try {
            InputStream reportStream = getClass().getResourceAsStream("/reports/relatorio_principal.jrxml");

            if (reportStream == null) {
                throw new FileNotFoundException("Arquivo relatorio_principal.jrxml não encontrado em resources/reports/");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            Map<String, Object> registro = new HashMap<>();
            registro.put("nomeRazao", "IRANI DE JESUS DO SANTOS");
            registro.put("documento", "056.878.915-60");
            registro.put("logradouro", "R. Z, 56");
            registro.put("bairro", "BAIUCA");
            registro.put("codigoAutenticidade", "B6D0E875");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(registro));

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("municipio", "SECRETARIA MUNICIPAL DE FINANÇAS");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    // === 2. MÉTODO DO DASHBOARD CORRIGIDO (Prints no lugar certo) ===
    public byte[] gerarRelatorioDashboard(LocalDate dataInicio, LocalDate dataFim, Long veiculoId, ResumoPainelDTO resumo, StatusFrotaDTO status) {
        try {
            InputStream reportStream = getClass().getResourceAsStream("/reports/relatorio_principal.jrxml");

            if (reportStream == null) {
                throw new FileNotFoundException("Arquivo relatorio_dashboard.jrxml não encontrado em resources/reports/");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            Map<String, Object> parameters = new HashMap<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            parameters.put("PERIODO", dataInicio.format(formatter) + " até " + dataFim.format(formatter));
            parameters.put("VEICULO_FILTRADO", veiculoId != null ? "Veículo ID: " + veiculoId : "Todos os Veículos");

            // Dados do Card de Resumo
            parameters.put("KM_TOTAL", resumo.getKmTotal());
            parameters.put("TOTAL_VIAGENS", resumo.getTotalViagens());
            parameters.put("CUSTO_MEDIO_KM", resumo.getCustoMedioKm());

            // Dados dos Cards de Status
            parameters.put("OPERANDO", status.getOperando());
            parameters.put("MANUTENCAO", status.getManutencao());
            parameters.put("PARADOS", status.getParados());
            parameters.put("DISPONIBILIDADE", status.getDisponibilidade());

            // LOG DE AJUDA: Imprime no console do STS/IntelliJ os dados exatos enviados pro Jasper
            System.out.println("=== MAPA DE PARAMETROS ENVIADO PARA O JASPER ===");
            parameters.forEach((key, value) -> System.out.println(key + " : " + value));
            System.out.println("================================================");

            // Preenche e exporta o relatório uma única vez
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}