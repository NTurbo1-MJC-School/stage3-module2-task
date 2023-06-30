package com.mjc.school.service.validator;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;
import static com.mjc.school.service.exceptions.ServiceErrorCode.VALIDATE_STRING_LENGTH;

public class AuthorValidator {
    private static final String AUTHOR_ID = "Author id";
    private static final String AUTHOR_NAME = "Author name";
    private static final Integer AUTHOR_NAME_MIN_LENGTH = 5;
    private static final Integer AUTHOR_NAME_MAX_LENGTH = 15;
    private static final Integer MAX_AUTHOR_ID = 20;
    private static AuthorValidator authorValidator;

    public static AuthorValidator getAuthorValidator() {
        if (authorValidator == null) {
            authorValidator = new AuthorValidator();
        }
        return authorValidator;
    }

    public void validateAuthorDto(AuthorDtoRequest dtoRequest) {
        validateAuthorId(dtoRequest.id());
        validateString(dtoRequest.name(), AUTHOR_NAME, AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);

    }

    public void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
        if (authorId > MAX_AUTHOR_ID) {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }

    private void validateNumber(Long id, String parameter) {
        if (id == null || id < 1) {
            throw new ValidatorException(
                    String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), parameter, parameter, id));
        }
    }

    private void validateString(String value, String parameter, int minLength, int maxLength) {
        if (value == null) {
            throw new ValidatorException(
                    String.format(VALIDATE_NULL_STRING.getMessage(), parameter, parameter));
        }
        if (value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new ValidatorException(
                    String.format(
                            VALIDATE_STRING_LENGTH.getMessage(),
                            parameter,
                            minLength,
                            maxLength,
                            parameter,
                            value));
        }
    }
}
