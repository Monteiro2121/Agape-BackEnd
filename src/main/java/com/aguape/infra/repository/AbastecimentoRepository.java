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

    @Query(value = "SELECT pab_codigo, AVG(aba_valor_pago / NULLIF(aba_quantidade, 0)) as preco_medio " +
            "FROM agfrota.tb_aba_abastecimento " +
            "WHERE pab_codigo IS NOT NULL AND aba_quantidade > 0 " +
            "GROUP BY pab_codigo " +
            "ORDER BY preco_medio ASC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> buscarMediaPrecosPostosNativo();
}