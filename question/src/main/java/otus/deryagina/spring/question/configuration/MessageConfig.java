package otus.deryagina.spring.question.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/bundle");
        messageSource.setDefaultEncoding("cp1251");
        return messageSource;
    }

    @Bean
    public Locale locale(@Value("${local.language}") String localLanguage,
                         @Value("${local.region}") String localRegion) {
        return new Locale(localLanguage, localRegion);
    }
}
