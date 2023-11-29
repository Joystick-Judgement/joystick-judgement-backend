package com.joystickjudgement.msgame.core.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString
public class ResponseError {

    private final Set<BusinessResponseError> errors = new HashSet<>();

    public ResponseError(BusinessResponseError error) {
        errors.add(error);
    }

    public ResponseError(Collection<BusinessResponseError> errors) {
        this.errors.addAll(errors);
    }

}
