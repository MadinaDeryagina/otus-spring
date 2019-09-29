package otus.deryagina.spring.question;


import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {

    private static int NUMBER_OF_ATTEMPTS_FOR_FULL_NAME=3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your full name: ");
        String fullName = validFullName(scanner);
        if( fullName == null){
            System.exit(0);
        }



    }

    private static String validFullName(Scanner scanner) {
        for (int i = 0; i < NUMBER_OF_ATTEMPTS_FOR_FULL_NAME ; i++) {
            String fullName = scanner.nextLine();
            if( StringUtils.isEmpty(fullName) || StringUtils.isBlank(fullName) ){
                System.out.println("Your input is empty, please try again. Your have " +
                        NUMBER_OF_ATTEMPTS_FOR_FULL_NAME + " attempts. Enter your full name again");
            }  else{
                return fullName;
            }
        }
        return null;
    }

}
