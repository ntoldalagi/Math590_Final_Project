import java.util.*;
import java.io.*;
import java.io.IOException;
import java.lang.StringBuffer;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class LineCreator {
  private String fileName;
  private String f2;
  private static String dir;
  private ArrayList<Double> inYData;
  private ArrayList<Double> inXData;
  private double slope;
  private double yIntercept;
  private ArrayList<Coordinate> lineCoordinates;
  private ArrayList<Coordinate> scatterCoordinates;

  public LineCreator(String in1, String in2) {
    fileName = in1;
    f2 = in2;
    // JFileChooser chooser= new JFileChooser();
    // int choice = chooser.showOpenDialog(null);
    dir = new File("").getAbsolutePath();
    inYData = readFile(fileName);
    inXData = readFile(f2);
    Regressor r = new Regressor(inXData, inYData);
    slope = r.getFinalSlope();
    yIntercept = r.getFinalIntercept();
    lineCoordinates = new ArrayList<Coordinate>();
    scatterCoordinates = new ArrayList<Coordinate>();
    makeCoordinates();
    Grapher g = new Grapher("Tesla", lineCoordinates, scatterCoordinates);
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

  public void makeCoordinates() {
    int min = Math.min(inXData.size(), inYData.size());
    scatterCoordinates = new ArrayList<Coordinate>();
    lineCoordinates = new ArrayList<Coordinate>();
    double finalX = 0.0;
    for(int i = 0; i < min ; i++) {
      scatterCoordinates.add(new Coordinate(inXData.get(i), inYData.get(i)));
      lineCoordinates.add(new Coordinate(inXData.get(i), Regressor.function(inXData.get(i), slope, yIntercept)));
      finalX = inXData.get(i);
    }
    finalX += 2.0;
    int maxY = Grapher.findMax(scatterCoordinates, false) + 10;
    System.out.println("Max y: " + maxY);
    while(Regressor.function(finalX, slope, yIntercept) < maxY) {
      lineCoordinates.add(new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
      finalX+= 2.0;
    }
    lineCoordinates.add(new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
    lineCoordinates.add(0 , new Coordinate(0, yIntercept));
    int minY = Grapher.findMin(scatterCoordinates, false) - 10;
    finalX = yIntercept;
    while(Regressor.function(finalX, slope, yIntercept) > minY) {
      lineCoordinates.add(0 , new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
      finalX -= 2.0;
    }
    lineCoordinates.add(0 , new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
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
