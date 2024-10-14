package br.com.adriankich.desafio.votacao.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.StringUtils;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, String> {
    /**
     * isValid
     *
     * @param value String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
    @Override
    public boolean isValid( String value, ConstraintValidatorContext context )
    {
        /**
         * if value is null, will pass to @IsNotEmpty
         */
        if( StringUtils.isEmpty( value ))
        {
            return true;
        }

        return isCpf( value )
                ? validateCpf( value, context )
                : validateCnpj( value, context );
    }

    /**
     * isCpf
     *
     * @param value String
     * @return boolean
     */
    public boolean isCpf( String value )
    {
        return value.length() == 11;
    }

    /**
     * validateCpf
     *
     * @param value String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
    public boolean validateCpf( String value, ConstraintValidatorContext context )
    {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize( null );

        return cpfValidator.isValid( value, context );
    }

    /**
     * validateCnpj
     *
     * @param value String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
    public boolean validateCnpj( String value, ConstraintValidatorContext context )
    {
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize( null );

        return cnpjValidator.isValid( value, context );
    }
}
