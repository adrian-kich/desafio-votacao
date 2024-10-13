package br.com.adriankich.desafio.votacao.domain.model;

import br.com.adriankich.desafio.votacao.domain.enums.AssociadoEnum;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "associados")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String document;

    @Enumerated(EnumType.STRING)
    private AssociadoEnum status;
}
