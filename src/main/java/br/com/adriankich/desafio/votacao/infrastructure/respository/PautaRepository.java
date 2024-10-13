package br.com.adriankich.desafio.votacao.infrastructure.respository;

import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
