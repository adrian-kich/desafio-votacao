package br.com.adriankich.desafio.votacao.domain.service.impl;

import br.com.adriankich.desafio.votacao.application.v1.dto.PautaRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.ResultResponseDTO;
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

    /**
     * getPautas
     *
     * @return List<Pauta>
     */
    public List<Pauta> getPautas() {
        return pautaRepository.findAll().stream().map(pauta -> updatePauta(pauta)).toList();
    }

    /**
     * getPautaById
     *
     * @param id Long
     * @return Pauta
     */
    public Pauta getPautaById(Long id) {
        Pauta pauta = pautaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado uma pauta com o id: #" + id));

        updatePauta(pauta);
        return pauta;
    }

    /**
     * createPauta
     *
     * @param pautaDTO PautaRequestDTO
     * @return Pauta
     */
    public Pauta createPauta(PautaRequestDTO pautaDTO) {
        Pauta pauta = Pauta.builder()
                .title(pautaDTO.getTitle())
                .description(pautaDTO.getDescription())
                .creationDate(LocalDateTime.now())
                .status(PautaEnum.VOTACAO_NAO_INICIADA)
                .build();

        return addPauta(pauta);
    }

    /**
     * addPauta
     *
     * @param pauta Pauta
     * @return Pauta
     */
    public Pauta addPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    /**
     * updatePauta
     *
     * @param pauta Pauta
     * @return Pauta
     */
    public Pauta updatePauta(Pauta pauta) {
        if (pauta.getSessaoVotacao() != null)
            sessaoVotacaoService.updateSessaoVotacao(pauta.getSessaoVotacao());

        pauta.updateStatus();
        return pautaRepository.save(pauta);
    }

    /**
     * startSessaoVotacao
     *
     * @param pautaId Long
     * @param sessaoDTO SessaoVotacaoRequestDTO
     * @return SessaoVotacao
     */
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

    /**
     * getResult
     *
     * @param pautaId Long
     * @return ResultResponseDTO
     */
    public ResultResponseDTO getResult(Long pautaId) {
        Pauta pauta = getPautaById(pautaId);

        SessaoVotacao sessaoVotacao = pauta.getSessaoVotacao();

        if(sessaoVotacao != null) {
            ResultResponseDTO resultDTO = ResultResponseDTO.builder()
                    .id(pauta.getId())
                    .title(pauta.getTitle())
                    .description(pauta.getDescription())
                    .startTimeVoting(sessaoVotacao.getStartTime())
                    .endTimeVoting(sessaoVotacao.getEndTime())
                    .result(sessaoVotacao.getResult().getValue())
                    .approved(sessaoVotacao.getTotalApproved())
                    .reproved(sessaoVotacao.getTotalReproved())
                    .total(((long) sessaoVotacao.getVotos().size()))
                    .build();

            return resultDTO;
        } else {
            throw new NotFoundException("Não foi iniciada uma sessão de votação para esta pauta");
        }
    }
}
