package otus.deryagina.spring.question;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import otus.deryagina.spring.question.interact.InteractService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(Main.class);
        InteractService interactService =  context.getBean(InteractService.class);
        interactService.startInteraction();
    }
}
