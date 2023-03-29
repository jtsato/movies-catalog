package io.github.jtsato.moviescatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jorge Takeshi Sato
 */

@SpringBootApplication(scanBasePackageClasses = {MoviesCatalogApplication.class})
public class MoviesCatalogApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MoviesCatalogApplication.class);
    }
}
