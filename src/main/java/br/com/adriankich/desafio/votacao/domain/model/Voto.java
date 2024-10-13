package br.com.adriankich.desafio.votacao.domain.model;

import br.com.adriankich.desafio.votacao.domain.enums.VotoEnum;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "votos")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ref_sessao")
    private SessaoVotacao sessao;

    @ManyToOne
    @JoinColumn(name = "ref_associado")
    private Associado associado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VotoEnum voto;
}
