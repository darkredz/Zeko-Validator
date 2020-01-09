package com.zeko.validation;

import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public class PreConditionString extends PreCondition<String, ValidationEngineString> {

    public PreConditionString(ValidationEngine<String, ValidationEngineString> validationEngine) {
        super(validationEngine);
    }


    /**
     * Set a blank value to be considered as a validation error.
     * <p>
     * null       = blank
     * ""         = blank
     * " "        = blank
     * "bob"      = not blank
     * "  bob  "  = not blank
     *
     * @param message error message
     * @return validation engine
     */
    public ValidationEngineString mustNotBeBlank(String message) {
        this.validationEngine.setNullIsValid(false);
        this.validationEngine.mustFatally(PredicateUtil.isNotBlank(), message, "mustNotBeBlank");
        return (ValidationEngineString) this.validationEngine;
    }

    public ValidationEngineString mustNotBeBlank() {
        return mustNotBeBlank(null);
    }

    @Override
    public ValidationEngineString mustNotBeNull(String message) {
        return (ValidationEngineString) super.mustNotBeNull(message);
    }

    @Override
    public ValidationEngineString required(String message) {
        this.validationEngine.setNullIsValid(false);
        Predicate<String> p = (s) -> nonNull(s) && !PredicateUtil.isBlank(s) && !PredicateUtil.isTrimmedEmptyString(s);
        this.validationEngine.mustFatally(p, message, "required");
        return (ValidationEngineString) this.validationEngine;
    }

    public ValidationEngineString required() {
        return required(null);
    }

    public ValidationEngineString requiredLoose(String message) {
        this.validationEngine.setNullIsValid(false);
        Predicate<String> p = (s) -> nonNull(s) && !PredicateUtil.isBlank(s);
        this.validationEngine.mustFatally(p, message, "requiredLoose");
        return (ValidationEngineString) this.validationEngine;
    }

    public ValidationEngineString requiredLoose() {
        return requiredLoose(null);
    }

    @Override
    public ValidationEngineString mustNotBeNullWhen(boolean value, String message) {
        return (ValidationEngineString) super.mustNotBeNullWhen(value, message);
    }

    @Override
    public ValidationEngineString canBeNull() {
        return (ValidationEngineString) super.canBeNull();
    }

    public ValidationEngineString optional() {
        this.validationEngine.setNullIsValid(true);
        return (ValidationEngineString) this.validationEngine;
    }

    @Override
    public PreConditionString validateWhen(boolean validate) {
        return (PreConditionString) super.validateWhen(validate);
    }
}
