package br.com.adriankich.desafio.votacao.domain.model;

import br.com.adriankich.desafio.votacao.domain.enums.SessaoEnum;
import br.com.adriankich.desafio.votacao.domain.enums.VotoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sessao_votacao")
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ref_pauta")
    private Pauta pauta;

    @OneToMany
    private List<Voto> votos;

    private Long totalApproved;

    private Long totalReproved;

    @Enumerated(EnumType.STRING)
    private SessaoEnum status;

    @Enumerated(EnumType.STRING)
    private SessaoEnum result;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    /**
     * SessaoVotacao
     *
     * @param pauta
     * @param minutes
     */
    public SessaoVotacao(Pauta pauta, Long minutes) {
        this.pauta = pauta;
        this.status = SessaoEnum.ABERTA;
        this.startTime = LocalDateTime.now();
        this.endTime = startTime.plusMinutes(minutes);
        this.result = SessaoEnum.EM_VOTACAO;
        this.votos = new ArrayList<>();
        this.totalReproved = 0l;
        this.totalApproved = 0l;
    }

    /**
     * updateStatus
     */
    public void updateStatus() {
        if(endTime.isBefore(LocalDateTime.now())) {
            this.status = SessaoEnum.ENCERRADA;
        }
        updateResult();
    }

    /**
     * addVoto
     *
     * @param voto Voto
     */
    public void addVoto(Voto voto) {
        this.votos.add(voto);
    }

    /**
     * updateResult
     */
    public void updateResult() {
        if (this.status.equals(SessaoEnum.ABERTA)) {
            this.result = SessaoEnum.EM_VOTACAO;
        } else {
            this.totalApproved = votos.stream().filter(voto -> voto.getVoto().equals(VotoEnum.SIM)).count();
            this.totalReproved = votos.stream().filter(voto -> voto.getVoto().equals(VotoEnum.NAO)).count();

            if (this.totalApproved == this.totalReproved) {
                this.result = SessaoEnum.EMPATADA;
            } else if (this.totalApproved > this.totalReproved) {
                this.result = SessaoEnum.APROVADA;
            } else {
                this.result = SessaoEnum.REPROVADA;
            }
        }
    }
}
