package com.aguape.infra.repository;

// ADICIONE ESTA LINHA:
import com.aguape.dto.ConsumoMensalDTO;
import com.aguape.dto.PostoPrecoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.aguape.infra.model.Abastecimento; // Verifique se o caminho da model está certo
import java.util.List;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    @Query("SELECT new com.aguape.dto.ConsumoMensalDTO(to_char(a.data, 'YYYY-MM'), SUM(a.valorTotal)) " +
            "FROM Abastecimento a WHERE (:veiculoId IS NULL OR a.veiculo.id = :veiculoId) " +
            "GROUP BY to_char(a.data, 'YYYY-MM') ORDER BY 1")
    List<ConsumoMensalDTO> buscarConsumoMensal(@Param("veiculoId") Long veiculoId);

    @Query("SELECT new com.aguape.dto.PostoPrecoDTO(a.posto, AVG(a.valorLitro)) " +
            "FROM Abastecimento a GROUP BY a.posto")
    List<PostoPrecoDTO> buscarMediaPrecosPostos();
}
