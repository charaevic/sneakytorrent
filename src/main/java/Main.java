import com.google.gson.Gson;

import java.util.regex.Pattern;
// import com.dampcake.bencode.Bencode; - available if you need it!

public class Main {
  private static final Gson gson = new Gson();

  public static void main(String[] args) throws Exception {
    String command = args[0];
    if("decode".equals(command)) {

        String bencodedValue = args[1];
        String decoded;
        try {

          decoded = decodeBencode(bencodedValue);


        } catch(RuntimeException e) {
          System.out.println(e.getMessage());
          return;
      }

      try{
        long integerParse = Long.parseLong(decoded);
        System.out.println(gson.toJson(integerParse));
      }catch(Exception e) {
        System.out.println(gson.toJson(decoded));
      }

    } else {
      System.out.println("Unknown command: " + command);
    }

  }

  static String decodeBencode(String bencodedString) {
    if (Character.isDigit(bencodedString.charAt(0))) {
      int firstColonIndex = 0;
      for(int i = 0; i < bencodedString.length(); i++) { 
        if(bencodedString.charAt(i) == ':') {
          firstColonIndex = i;
          break;
        }
      }
      int length = Integer.parseInt(bencodedString.substring(0, firstColonIndex));
      return bencodedString.substring(firstColonIndex+1, firstColonIndex+1+length);
    } else if (bencodedString.charAt(0) == 'i'){


        if(!(bencodedString.charAt(bencodedString.length()-1) == 'e')) {
          throw new RuntimeException("Improper format. Check and try again.");

      }

      int startIndex = 1;


          String cropped = bencodedString.substring(startIndex, bencodedString.length() - 1);

          return cropped;




    }else{
      throw new RuntimeException("Only strings and integers supported.");
    }
  }
  
}
