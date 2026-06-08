package com.aguape.infra.repository;

import com.aguape.infra.model.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    @Query(value = "SELECT to_char(a.aba_dt_abastecimento, 'YYYY-MM') as mes, SUM(a.aba_valor_pago) as valor " +
            "FROM agfrota.tb_aba_abastecimento a " +
            "WHERE (:veiculoId IS NULL OR a.vei_codigo = :veiculoId) " +
            "GROUP BY to_char(a.aba_dt_abastecimento, 'YYYY-MM') " +
            "ORDER BY 1", nativeQuery = true)
    List<Object[]> buscarConsumoMensal(@Param("veiculoId") Long veiculoId);

    @Query(value = "SELECT pab_codigo, AVG(aba_valor_pago / aba_quantidade) " +
            "FROM agfrota.tb_aba_abastecimento " +
            "GROUP BY pab_codigo", nativeQuery = true)
    List<Object[]> buscarMediaPrecosPostosNativo();
}