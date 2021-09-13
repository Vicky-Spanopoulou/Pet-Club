package cb13.project.utility;

import java.util.ArrayList;
import java.util.Random;

public class GenerateVerificationCode {

   private static String LETTERS="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
   private static String NUMBERS="0123456789";
   private static String SPECIAL_CHARACTERS="dasfafdasfa";
   private static final String[]  CHARACTER_LIST={LETTERS,NUMBERS,SPECIAL_CHARACTERS};

   public static String codeGenerator(int numberOfCharacters){
       String result="";
       Random rand = new Random();
       for (int i = 0; i < numberOfCharacters; i++) {
           int numberOfList=rand.nextInt(CHARACTER_LIST.length);
           char character=CHARACTER_LIST[numberOfList].charAt(rand.nextInt(CHARACTER_LIST[numberOfList].length()));
           result += character;
       }
       System.out.println(result);
       return result;

   }


}
