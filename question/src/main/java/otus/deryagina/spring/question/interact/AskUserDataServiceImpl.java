package otus.deryagina.spring.question.interact;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import otus.deryagina.spring.question.iostreams.IOStreamsProvider;

import java.util.Locale;

@AllArgsConstructor
@Service
public class AskUserDataServiceImpl implements AskUserDataService {

    private final IOStreamsProvider ioStreamsProvider;

    private final MessageSource messageSource;

    private final Locale locale;

    @Override
    public String getFullName(int numberOfAttempts) {
        ioStreamsProvider.printInfo(messageSource.getMessage("enter.fullname", null, locale));
        String fullName = validFullName(numberOfAttempts);
        if (fullName != null) {
            ioStreamsProvider.printInfo(messageSource.getMessage("hello.user", new String[]{fullName},locale));
        }
        return fullName;
    }

    private String validFullName(int numberOfAttempts) {
        for (int i = 0; i < numberOfAttempts; i++) {
            String fullName = ioStreamsProvider.readData();
            if ((StringUtils.isEmpty(fullName) || StringUtils.isBlank(fullName))) {
                if (i != numberOfAttempts - 1) {
                    ioStreamsProvider.printInfo(messageSource.getMessage("retry.enter.name",
                            new String[]{String.valueOf(numberOfAttempts - i - 1)}, locale));
                } else {
                    return null;
                }
            } else {
                return fullName;
            }
        }
        return null;
    }

}
