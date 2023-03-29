package io.github.jtsato.moviescatalog.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author Jorge Takeshi Sato
 */

public class LocaleChangeHeaderInterceptor extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {

        final String newLocale = request.getHeader(getParamName());

        if (newLocale != null) {
            setNewLocale(request, response, newLocale, getLocaleResolver(request));
        }

        return true;
    }

    private LocaleResolver getLocaleResolver(HttpServletRequest request) {
        final LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }
        return localeResolver;
    }

    private void setNewLocale(final HttpServletRequest request, final HttpServletResponse response, final String locale, final LocaleResolver localeResolver) {
        try {
            localeResolver.setLocale(request, response, parseLocaleValue(locale));
        } catch (final IllegalArgumentException ex) {
            if (!isIgnoreInvalidLocale()) {
                throw ex;
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Ignoring invalid locale value [" + locale + "]: " + ex.getMessage());
            }
        }
    }
}
