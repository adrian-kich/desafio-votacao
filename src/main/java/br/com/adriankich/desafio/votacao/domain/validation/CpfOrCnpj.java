package br.com.adriankich.desafio.votacao.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint( validatedBy = CpfOrCnpjValidator.class )
@Target({ ElementType.FIELD })
@Retention( RetentionPolicy.RUNTIME )
public @interface CpfOrCnpj {
    String message() default "CPF ou CNPJ inválido. Informe apenas os números, sem pontos ou traços";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
