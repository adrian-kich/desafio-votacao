package br.com.adriankich.desafio.votacao.domain.service.impl;

import br.com.adriankich.desafio.votacao.application.v1.dto.VotoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.enums.SessaoEnum;
import br.com.adriankich.desafio.votacao.domain.enums.VotoEnum;
import br.com.adriankich.desafio.votacao.domain.exception.AlreadyVotedException;
import br.com.adriankich.desafio.votacao.domain.exception.NotFoundException;
import br.com.adriankich.desafio.votacao.domain.exception.SessaoIsNotInVotingException;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;
import br.com.adriankich.desafio.votacao.domain.service.SessaoVotacaoService;
import br.com.adriankich.desafio.votacao.infrastructure.respository.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private AssociadoServiceImpl associadoService;

    @Autowired
    private VotoServiceImpl votoService;

    /**
     * getSessaoVotacao
     *
     * @param id Long
     * @return
     */
    @Override
    public SessaoVotacao getSessaoVotacao(Long id) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado uma sessão com o id: #" + id));

        updateSessaoVotacao(sessaoVotacao);
        return sessaoVotacao;
    }

    /**
     * createSessao
     * @param pauta Pauta
     * @param minutes Long
     * @return SessaoVotacao
     */
    @Override
    public SessaoVotacao createSessao(Pauta pauta, Long minutes) {
        SessaoVotacao sessaoVotacao = new SessaoVotacao(pauta, minutes);

        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    /**
     * updateSessaoVotacao
     *
     * @param sessaoVotacao SessaoVotacao
     * @return SessaoVotacao
     */
    @Override
    public SessaoVotacao updateSessaoVotacao(SessaoVotacao sessaoVotacao) {
        sessaoVotacao.updateStatus();
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    /**
     * addVoto
     *
     * @param sessaoVotacaoId Long
     * @param votoDTO VotoRequestDTO
     * @return Voto
     */
    @Override
    public Voto addVoto(Long sessaoVotacaoId, VotoRequestDTO votoDTO) {
        SessaoVotacao sessaoVotacao = getSessaoVotacao(sessaoVotacaoId);

        if (sessaoVotacao.getStatus().equals(SessaoEnum.ABERTA)) {
            List<Voto> votos = sessaoVotacao.getVotos();

            boolean alreadyVoted = votos.stream()
                    .anyMatch(voto -> voto.getAssociado().getId().equals(votoDTO.getAssociadoId()));

            if(alreadyVoted) throw new AlreadyVotedException("Associado já votou nessa sessão de votação");

            Associado associado = associadoService.getAssociadoById(votoDTO.getAssociadoId());
            VotoEnum votoEnum = VotoEnum.valueOf(votoDTO.getVoto());
            Voto voto = votoService.createVoto(sessaoVotacao, associado, votoEnum);

            sessaoVotacao.addVoto(voto);
            sessaoVotacao.setVotos(votos);

            updateSessaoVotacao(sessaoVotacao);
            return voto;
        } else {
            throw new SessaoIsNotInVotingException("Essa sessão não está aberta para votação");
        }
    }
}
