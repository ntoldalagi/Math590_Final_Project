import java.util.*;
import java.io.*;
import java.io.IOException;
import java.lang.StringBuffer;
import java.util.ArrayList;

public class LineCreator {
  private String fileName;
  private String f2;
  private static String dir;
  private ArrayList<Double> inYData;
  private ArrayList<Double> inXData;
  private double slope;

  public LineCreator(String in1, String in2) {
    fileName = in1;
    f2 = in2;
    dir = new File("").getAbsolutePath();
    inYData = readFile(fileName);
    inXData = readFile(f2);
    Regressor r = new Regressor(inXData, inYData);
  }

  public ArrayList<Double> readFile(String fName) {
    ArrayList<Double> data = new ArrayList<Double>();
    ArrayList<String> inputStrings = new ArrayList<String>();
    File f = new File(fName);
    System.out.println(f);
    String str = "";
    try {
      FileInputStream stream = new FileInputStream(f);
      InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
      BufferedReader input = new BufferedReader(reader);
      while((str = input.readLine()) != null) {
        inputStrings.add(str);
      }
      input.close();
    } catch (Throwable e) {
      System.out.println(e);
    }
    for(String s: inputStrings) {
      data.add(Double.parseDouble(s));
    }
    return data;
  }


  public static void main(String[] args) {
    String in1 = args[0];
    String in2 = args[1];
    if(in1.indexOf(".txt") == -1) {
      in1 += ".txt";
    }

    // System.out.println(in);
    LineCreator lc = new LineCreator(in1, in2);
  }

}
