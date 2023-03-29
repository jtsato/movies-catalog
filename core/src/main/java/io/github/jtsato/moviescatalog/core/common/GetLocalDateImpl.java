package io.github.jtsato.moviescatalog.core.common;

import java.time.LocalDate;

import jakarta.inject.Named;

/**
 * @author Jorge Takeshi Sato
 */

@Named
public class GetLocalDateImpl implements GetLocalDate {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
