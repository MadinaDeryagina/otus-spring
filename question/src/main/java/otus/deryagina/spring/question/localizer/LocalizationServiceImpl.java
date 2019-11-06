package otus.deryagina.spring.question.localizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;
    private final Locale locale;
    private final String localizedFileName;

    public LocalizationServiceImpl(MessageSource messageSource, @Value("${locale.name}") String localeName,
                                   @Value("${question.base.filename}") String baseFilename) {
        this.messageSource = messageSource;
        this.locale = new Locale(localeName);
        localizedFileName = baseFilename + "_" + localeName + ".csv";
    }

    @Override
    public String getLocalizedMessage(String key, String[] parameters) {
        return messageSource.getMessage(key, parameters, locale);
    }

    @Override
    public String getLocalizedQuestionFile() {
        return localizedFileName;
    }
}
