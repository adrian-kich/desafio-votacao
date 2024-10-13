package br.com.adriankich.desafio.votacao.infrastructure.respository;

import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
}
