package br.com.adriankich.desafio.votacao.domain.model;

import br.com.adriankich.desafio.votacao.domain.enums.PautaEnum;
import br.com.adriankich.desafio.votacao.domain.enums.SessaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pautas")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private PautaEnum status;

    @OneToOne
    @JoinColumn(name = "ref_sessao")
    private SessaoVotacao sessaoVotacao;

    @Column(name = "created_at")
    private LocalDateTime creationDate;

    public Pauta(String title, String description) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.status = PautaEnum.VOTACAO_NAO_INICIADA;
    }

    public void updateStatus() {
        if(this.sessaoVotacao != null) {
            if (this.sessaoVotacao.getResult().equals(SessaoEnum.APROVADA)) {
                this.status = PautaEnum.APROVADA;
            } else if (this.sessaoVotacao.getResult().equals(SessaoEnum.REPROVADA)) {
                this.status = PautaEnum.REPROVADA;
            } else if (this.sessaoVotacao.getResult().equals(SessaoEnum.EMPATADA)) {
                this.status = PautaEnum.EMPATADA;
            } else {
                this.status = PautaEnum.EM_VOTACAO;
            }
        } else {
            this.status = PautaEnum.VOTACAO_NAO_INICIADA;
        }
    }
}
