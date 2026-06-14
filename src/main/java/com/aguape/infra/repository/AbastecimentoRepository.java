package com.aguape.infra.repository;

import com.aguape.infra.model.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    // CORRIGIDO: Agora filtra por dataInicio e dataFim
    @Query(value = "SELECT to_char(a.aba_dt_abastecimento, 'YYYY-MM') as mes, SUM(a.aba_valor_pago) as valor " +
            "FROM agfrota.tb_aba_abastecimento a " +
            "WHERE (:veiculoId IS NULL OR a.vei_codigo = :veiculoId) " +
            "AND a.aba_dt_abastecimento BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY to_char(a.aba_dt_abastecimento, 'YYYY-MM') " +
            "ORDER BY 1", nativeQuery = true)
    List<Object[]> buscarConsumoMensal(
            @Param("veiculoId") Long veiculoId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );

    // CORRIGIDO: Agora aceita veiculoId (opcional) e filtra por período de data
    @Query(value = "SELECT pab_codigo, AVG(aba_valor_pago / NULLIF(aba_quantidade, 0)) as preco_medio " +
            "FROM agfrota.tb_aba_abastecimento " +
            "WHERE pab_codigo IS NOT NULL AND aba_quantidade > 0 " +
            "AND (:veiculoId IS NULL OR vei_codigo = :veiculoId) " +
            "AND aba_dt_abastecimento BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY pab_codigo " +
            "ORDER BY preco_medio ASC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> buscarMediaPrecosPostosNativo(
            @Param("veiculoId") Long veiculoId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );

    @Query(value = "SELECT COALESCE(SUM(aba_valor_pago), 0) FROM agfrota.tb_aba_abastecimento WHERE aba_dt_abastecimento BETWEEN :inicio AND :fim", nativeQuery = true)
    Double somarCustoTotalPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query(value = "SELECT COALESCE(SUM(aba_valor_pago), 0) FROM agfrota.tb_aba_abastecimento " +
            "WHERE aba_dt_abastecimento BETWEEN :inicio AND :fim " +
            "AND (:veiculoId IS NULL OR vei_codigo = :veiculoId)", nativeQuery = true)
    Double somarCustoTotalPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim, @Param("veiculoId") Long veiculoId);
}