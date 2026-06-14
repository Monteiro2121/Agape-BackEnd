package com.aguape.infra.repository;

import com.aguape.infra.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    @Query(value = "SELECT COALESCE(SUM(via_km_retorno - via_km_saida), 0) " +
            "FROM agfrota.tb_via_viagem " +
            "WHERE via_dt_saida BETWEEN :dataInicio AND :dataFim " +
            "AND vei_codigo = COALESCE(CAST(:veiculoId AS bigint), vei_codigo)", nativeQuery = true)
    Double somarKmTotalPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("veiculoId") Long veiculoId
    );

    @Query(value = "SELECT COUNT(via_codigo) " +
            "FROM agfrota.tb_via_viagem " +
            "WHERE via_dt_saida BETWEEN :dataInicio AND :dataFim " +
            "AND vei_codigo = COALESCE(CAST(:veiculoId AS bigint), vei_codigo)", nativeQuery = true)
    Long contarViagensPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("veiculoId") Long veiculoId
    );

    @Query(value = "SELECT to_char(v.via_dt_saida, 'YYYY-MM') as mes, " +
            "SUM(v.via_km_retorno - v.via_km_saida) as km_empresa, " +
            "0 as km_terceirizada " + // Retorna zero temporariamente para o front não quebrar
            "FROM agfrota.tb_via_viagem v " +
            "WHERE v.vei_codigo = COALESCE(CAST(:veiculoId AS bigint), v.vei_codigo) " +
            "AND v.via_dt_saida BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY to_char(v.via_dt_saida, 'YYYY-MM') " +
            "ORDER BY to_char(v.via_dt_saida, 'YYYY-MM')", nativeQuery = true)
    List<Object[]> buscarQuilometragemGrafico(
            @Param("veiculoId") Long veiculoId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );

    // Adicione estes métodos no seu ViagemRepository.java

    @Query(value = "SELECT COUNT(DISTINCT vei_codigo) FROM agfrota.tb_via_viagem WHERE via_dt_saida BETWEEN :inicio AND :fim", nativeQuery = true)
    Long contarVeiculosOperandoNoPeriodo(@Param("inicio") java.time.LocalDate inicio, @Param("fim") java.time.LocalDate fim);

    @Query(value = "SELECT COUNT(via_codigo) FROM agfrota.tb_via_viagem WHERE vei_codigo = :veiculoId AND via_dt_saida BETWEEN :inicio AND :fim", nativeQuery = true)
    Long contarViagensDoVeiculoNoPeriodo(@Param("veiculoId") Long veiculoId, @Param("inicio") java.time.LocalDate inicio, @Param("fim") java.time.LocalDate fim);
}