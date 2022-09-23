class Main {
  private static final String refString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

  public static String encode(String plainText, char code){
    // return plainText if code not in refString
    if (!refString.contains(Character.toString(code))) {
      return plainText;
    } else {
      // Convert toCharArray eg. ['H', 'E', 'L', 'L', 'O', ' ']
      char[] characterArray = plainText.toCharArray();

      // Find index of the code provided to offset eg. B - 1 
      String encodedText = Character.toString(code);
      int offset = refString.indexOf(encodedText);
      
      for (int i = 0; i < characterArray.length; i++) {
        int currentIndex = refString.indexOf(characterArray[i]);
        if (currentIndex != -1) {
          currentIndex -= offset;
          if (currentIndex >= 0) {
            characterArray[i] = refString.charAt(currentIndex);
          } else {
            currentIndex += 44;
            characterArray[i] = refString.charAt(currentIndex);
          }
        }
      }
      
      // Convert encodedText characterArray back to String
      String coded = String.valueOf(characterArray);
      encodedText += coded; 
      return encodedText;
    }
  }

  public static String decode(String encodedText){
    String decodeChar = Character.toString(encodedText.charAt(0));
    if (!refString.contains(decodeChar)) {
      return encodedText;
    } else {
      
      char[] characterArray = encodedText.toCharArray();
      int reset = refString.indexOf(decodeChar);

      for (int i = 1; i < characterArray.length; i++) {
        int currentIndex = refString.indexOf(characterArray[i]);
        if (currentIndex != -1) {
          currentIndex += reset;
          if (currentIndex <= 43) {
            characterArray[i] = refString.charAt(currentIndex);
          } else {
            currentIndex -= 44;
            characterArray[i] = refString.charAt(currentIndex);
          }
        }
      }
      
      String plainText = String.valueOf(characterArray);

      return plainText.substring(1); 
    }
  }
  
  public static void main(String[] args) {
    String text = "HELLO WORLD";
    String encoded = encode(text, 'F');
    System.out.println(encoded);
    
    String encoded2 = encode(text, 'f');
    System.out.println(encoded2);
    
    String decoded = decode(encode(text, 'F'));
    System.out.println(decoded);
    
    String text1 = "#C/GGJ RJMG.";
    String decoded2 = decode(text1);
    System.out.println(decoded2);
  }
}