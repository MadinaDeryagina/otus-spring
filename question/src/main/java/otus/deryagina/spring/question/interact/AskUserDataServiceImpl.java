package otus.deryagina.spring.question.interact;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import otus.deryagina.spring.question.iostreams.IOStreamsProvider;

@AllArgsConstructor
public class AskUserDataServiceImpl implements AskUserDataService {

    IOStreamsProvider ioStreamsProvider;

    public String getFullName(int numberOfAttempts) {
        ioStreamsProvider.printInfo("Enter your full name: ");
        String fullName = validFullName(numberOfAttempts);
        if(fullName != null) {
            ioStreamsProvider.printInfo("Hello " + fullName);
        }
        return fullName;
    }

    private String validFullName(int numberOfAttempts) {
        for (int i = 0; i < numberOfAttempts; i++) {
            String fullName = ioStreamsProvider.readNexLineData();
            if ((StringUtils.isEmpty(fullName) || StringUtils.isBlank(fullName))) {
                if (i != numberOfAttempts - 1) {
                    ioStreamsProvider.printInfo("Your input is empty, please try again. Your have " +
                            String.valueOf(numberOfAttempts - i - 1) + " attempts. Enter your full name again");
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
