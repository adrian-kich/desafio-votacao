package br.com.adriankich.desafio.votacao.domain.service.impl;

import br.com.adriankich.desafio.votacao.application.v1.dto.PautaRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.enums.PautaEnum;
import br.com.adriankich.desafio.votacao.domain.exception.AlreadyExistsException;
import br.com.adriankich.desafio.votacao.domain.exception.NotFoundException;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.service.PautaService;
import br.com.adriankich.desafio.votacao.infrastructure.respository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoServiceImpl sessaoVotacaoService;

    public List<Pauta> getPautas() {
        return pautaRepository.findAll().stream().map(pauta -> updatePauta(pauta)).toList();
    }

    public Pauta getPautaById(Long id) {
        Pauta pauta =  pautaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado uma pauta com o id: #" + id));

        updatePauta(pauta);
        return pauta;
    }

    public Pauta createPauta(PautaRequestDTO pautaDTO) {
        Pauta pauta = Pauta.builder()
                .title(pautaDTO.getTitle())
                .description(pautaDTO.getDescription())
                .creationDate(LocalDateTime.now())
                .status(PautaEnum.VOTACAO_NAO_INICIADA)
                .build();

        return addPauta(pauta);
    }

    public Pauta addPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Pauta updatePauta(Pauta pauta) {
        if (pauta.getSessaoVotacao() != null)
            sessaoVotacaoService.updateSessaoVotacao(pauta.getSessaoVotacao());

        pauta.updateStatus();
        return pautaRepository.save(pauta);
    }

    public SessaoVotacao startSessaoVotacao(Long pautaId, SessaoVotacaoRequestDTO sessaoDTO) {
        Pauta pauta = getPautaById(pautaId);

        Long minutes = sessaoDTO.getMinutes() != null ? sessaoDTO.getMinutes() : Pauta.DEFAULT_SESSAO_TIME;

        if (pauta.getSessaoVotacao() == null) {
            SessaoVotacao sessaoVotacao = sessaoVotacaoService.createSessao(pauta, minutes);

            pauta.setSessaoVotacao(sessaoVotacao);
            pauta.updateStatus();

            updatePauta(pauta);

            return sessaoVotacao;
        } else {
            throw new AlreadyExistsException("Já existe uma sessão de votação para esta pauta. Id da sessão: #" + pauta.getSessaoVotacao().getId());
        }
    }
}
