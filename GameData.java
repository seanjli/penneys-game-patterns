import java.util.*;
import java.lang.*;

class Main {

  static final int Q = 4; // Q, or # of letters in the alphabet (Q <= 10)

  static final int L = 4; // L, or # of letters in the pattern

  static ArrayList<String> list = uniquePatterns(L); // List of unique patterns with alphabet size Q and length L

  /**
  ############
  ### MAIN ###
  ############
  **/

  public static void main(String[] args) {
    /**
      All methods are listed below. Uncomment each one to print it.
    **/

    // listAllPatterns(); // Lists all patterns
    // listNumOfPatterns; // Lists # of patterns
    // listAllCLNs(); // Lists all CLNs of patterns
    // listAllMatches(); // Lists odds for all pairs
    // listAllMatchesShort(); // Lists odds for all pairs
    // listBestBeater(); // Lists best choice for Bob
    // listBestBeaterShort(); // Lists best choice for Bob
  }

  /**
  ###############
  ### METHODS ###
  ###############
  **/

  // Lists all patterns of length L

  public static void listAllPatterns() {
    System.out.println(list + "\n");
  }

  // Lists number of patterns of length L

  public static void listNumOfPatterns() {
    System.out.println(list.size() + "\n");
  }

  // Displays all CLNs

  public static void listAllCLNs() {
     for (int i = 0; i < list.size(); i++) {
       System.out.println(list.get(i) + " " + Arrays.toString(corr(list.get(i),list.get(i))) + " " + cln(corr(list.get(i),list.get(i))));
     }
  }

  // Displays Bob's odds of winning if Alice chooses p1 and Bob chooses p2

  public static void listAllMatches() {
    for (int i = 0; i < list.size(); i++) {
      String p1 = list.get(i);
      System.out.println("\np1 = " + p1 + "\n");

      // Runs over all patterns p2

      for (int j = 0; j < list.size(); j++) {

        if (i==j) 
          continue;
        
        String p2 = list.get(j);
        
        // Orbit sizes j!/k!

        int r1 = factorial(Q) / factorial(Q - countLetters(p1));
        int r2 = factorial(Q) / factorial(Q - countLetters(p2));

        // Computes probability

        double prob = ((double)(cln(corr(p1, p1)) - cln(corr(p1,p2))) / (cln(corr(p2, p2)) - cln(corr(p2,p1)))*(double)r2/r1);

        // Prints out info

        System.out.println("p2 = " + p2);
        System.out.println("G/r1 * (p1Lp1 - p1Lp2) = " + factorial(Q) + "/" + r1 + " * (" + cln(corr(p1,p1)) + " - " + cln(corr(p1,p2)) + ") = " + (factorial(Q)/r1 * (cln(corr(p1,p1)) - cln(corr(p1,p2)))));
        System.out.println("G/r2 * (p2Lp2 - p2Lp1) = " + factorial(Q) + "/" + r2 + " * (" + cln(corr(p2,p2)) + " - " + cln(corr(p2,p1)) + ") = " + (factorial(Q)/r2 * (cln(corr(p2,p2)) - cln(corr(p2,p1)))));
        System.out.println("odds = " + prob);
        System.out.println();
      }
    }
  }

  // Above, but abridged print output

  public static void listAllMatchesShort() {
    for (int i = 0; i < list.size(); i++) {
      String p1 = list.get(i);

      for (int j = 0; j < list.size(); j++) {

        if (i==j) 
          continue;
        
        String p2 = list.get(j);
        int r1 = factorial(Q) / factorial(Q - countLetters(p1));
        int r2 = factorial(Q) / factorial(Q - countLetters(p2));
        double prob = ((double)(cln(corr(p1, p1)) - cln(corr(p1,p2))) / (cln(corr(p2, p2)) - cln(corr(p2,p1)))*(double)r2/r1);

        System.out.println(p1 + " " + p2 + " " + prob);
      }
    }
  }

  // Displays optimal word choice for Bob (first lexicographical best choice)

  public static void listBestBeater() {
    for (int i = 0; i < list.size(); i++) {
      String p1 = list.get(i);
      System.out.println("p1 = " + p1);

      // Sets variables for best beater

      double maxprob = 0;
      String win = "";
      int mr1 = 0; int mr2 = 0;

      // Runs over all choices of p2

      for (int j = 0; j < list.size(); j++) {
        
        if (i==j) 
          continue;

        String p2 = list.get(j);

        // Orbit sizes

        int r1 = factorial(Q) / factorial(Q - countLetters(p1));
        int r2 = factorial(Q) / factorial(Q - countLetters(p2));
        
        // Checks for larger probability

        double prob = ((double)(cln(corr(p1, p1)) - cln(corr(p1,p2))) / (cln(corr(p2, p2)) - cln(corr(p2,p1)))*(double)r2/r1);
        if (maxprob < prob){
          mr1 = r1;
          mr2 = r2;
          maxprob = prob;
          win = p2;
        }
      }

      // Prints out info

      System.out.println("p2 = " + win);
        System.out.println("G/r1 * (p1Lp1 - p1Lp2) = " + factorial(Q) + "/" + mr1 + " * (" + cln(corr(p1,p1)) + " - " + cln(corr(p1,win)) + ") = " + (factorial(Q)/mr1 * (cln(corr(p1,p1)) - cln(corr(p1,win)))));
        System.out.println("G/r2 * (p2Lp2 - p2Lp1) = " + factorial(Q) + "/" + mr2 + " * (" + cln(corr(win,win)) + " - " + cln(corr(win,p1)) + ") = " + (factorial(Q)/mr2 * (cln(corr(win,win)) - cln(corr(win,p1)))));
        System.out.println("odds = " + maxprob);
        System.out.println();
    }
  }

  // Above, but abridged print output
  
  public static void listBestBeaterShort() {
    for (int i = 0; i < list.size(); i++) {
      String p1 = list.get(i);
      double maxprob = 0;
      String win = "";
      int mr1 = 0; int mr2 = 0;
      for (int j = 0; j < list.size(); j++) {
        
        if (i==j) 
          continue;

        String p2 = list.get(j);

        int r1 = factorial(Q) / factorial(Q - countLetters(p1));
        int r2 = factorial(Q) / factorial(Q - countLetters(p2));
        
        double prob = ((double)(cln(corr(p1, p1)) - cln(corr(p1,p2))) / (cln(corr(p2, p2)) - cln(corr(p2,p1)))*(double)r2/r1);
        if (maxprob < prob){
          mr1 = r1;
          mr2 = r2;
          maxprob = prob;
          win = p2;
        }
      }

      System.out.println(p1 + " " + win + " " + maxprob);
    }
  }

  /**
  ###############
  ### HELPERS ###
  ###############
  **/


  // Converts into bases

  public static String baseConversion(String number, int sBase, int dBase, int len) { 
    String output = Integer.toString( Integer.parseInt(number, sBase), dBase);
    int oldLen = output.length();
    for (int i = 0; i < len - oldLen; i++)
      output = "0" + output; 
    return output;
    }

  // Generates unique patterns

  public static ArrayList<String> uniquePatterns(int len) {
    ArrayList<String> output = new ArrayList<String>();
    for (int i = 0; i < Math.pow(Q,len); i++) {
      output.add(baseConversion(Integer.toString(i), 10, Q, len));
    }
    for (int i = 0; i < output.size(); i++) {
      String s = output.get(i);
      s = s.replace("0","a");
      s = s.replace("1","b");
      s = s.replace("2","c");
      s = s.replace("3","d");
      s = s.replace("4","e");
      s = s.replace("5","f");
      s = s.replace("6","g");
      s = s.replace("7","h");
      s = s.replace("8","i");
      s = s.replace("9","j");
      output.set(i,s);
    }
    for (int i = output.size()-1; i >= 0; i--) {
      for (int j = 0; j < i; j++) {
        if (equiv(output.get(i),output.get(j))) {
          output.remove(i);
          break;
        }
      }
    }
    return output;
  }

  // CLN from a correlation

  public static int cln(int[] arr) {
    int output = 0;
    for (int i = 0; i < arr.length; i++) {
      output += arr[i]*Math.pow(Q,arr.length-i-1);
    }
    return output;
  } 

  // Generate correlation

  public static int[] corr(String p1, String p2) {
    int[] output = new int[p1.length()];
    for (int i = 0; i < p1.length(); i++) {
      String x1 = p1.substring(i,p1.length());
      String x2 = p2.substring(0,p1.length()-i);
      if (!equiv(x1,x2)) {
        output[i] = 0;
        continue;
      }
      int k = countLetters(x1);
      int l = countLetters(p1);
      output[i] = factorial(Q-k) / factorial(Q-l);
    }

    return output;
  }

  // Factorial function

  public static int factorial(int n){    
  if (n == 0)    
    return 1;    
  else    
    return(n * factorial(n-1));    
  }

  // Counts number of distinct letters in word

  public static int countLetters(String w) {
    return (int)w.chars().distinct().count();
  }

  // Checks if two patterns are equivalent

  public static boolean equiv(String p1, String p2) {
    if (p1.length() != p2.length())
      return false;
    int k = 0; int l = 0;
    for (int i = 0; i < p1.length(); i++) {
      if (Character.isLetter(p1.charAt(i))) {
        p1 = p1.replaceAll(Character.toString(p1.charAt(i)), Integer.toString(k));
        k++;
      }
      if (Character.isLetter(p2.charAt(i))){
        p2 = p2.replaceAll(Character.toString(p2.charAt(i)), Integer.toString(l));
        l++;
      }
    }
    if (p1.equals(p2))
      return true;
    return false;
  }
}
