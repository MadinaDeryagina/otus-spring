package otus.deryagina.spring.question;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.deryagina.spring.question.interact.InteractService;


public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        InteractService interactService =  context.getBean(InteractService.class);
        interactService.startInteraction();
    }
}
