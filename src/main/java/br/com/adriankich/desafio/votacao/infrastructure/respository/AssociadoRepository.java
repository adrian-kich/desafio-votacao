package br.com.adriankich.desafio.votacao.infrastructure.respository;

import br.com.adriankich.desafio.votacao.domain.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    Optional<Associado> findByDocument(String document);
    boolean existsByDocument(String document);
}
