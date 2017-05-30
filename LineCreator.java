import java.util.*;
import java.io.*;
import java.io.IOException;
import java.lang.StringBuffer;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class LineCreator {
  private String fileName;
  private String f2;
  private String startDate = "";
  private static String dir;
  private ArrayList<Double> inYData;
  private double slope;
  private double yIntercept;
  private ArrayList<Coordinate> lineCoordinates;
  private ArrayList<Coordinate> scatterCoordinates;

  public LineCreator(String in1, String title) {
    // JFileChooser chooser= new JFileChooser();
    // int choice = chooser.showOpenDialog(null);
    inYData = readFile(in1);
    Regressor r = new Regressor(startDate, inYData);
    slope = r.getFinalSlope();
    yIntercept = r.getFinalIntercept();
    lineCoordinates = new ArrayList<Coordinate>();
    scatterCoordinates = new ArrayList<Coordinate>();
    makeCoordinates();
    Grapher g = new Grapher(title, lineCoordinates, scatterCoordinates, startDate);
  }

  public ArrayList<Double> readFile(String fName) {
    ArrayList<Double> data = new ArrayList<Double>();
    ArrayList<String> inputStrings = new ArrayList<String>();
    File f = new File(fName);
    String str = "";
    String str1 = "";
    try {
      FileInputStream stream = new FileInputStream(f);
      InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
      BufferedReader input = new BufferedReader(reader);
      int i = 0;
      while((str = input.readLine()) != null) {
        i++;
        if(str.replaceAll("[^.-9]", "").length() == 0) {
          continue;
        } else if(i == 1) {
          continue;
        } else {
          str = str.replaceAll("\\s+", "X");
          str = str.replaceAll("[^.-9] || ^\\s", "");
          String[] arr = str.split("X");
          for(int a = 0; a< arr.length; a++) {
            arr[a] = arr[a].replaceAll("[^.-9]", "");
          }
          startDate = arr[0];
          inputStrings.add(arr[1]);
        }
        str1 = str;
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
    int min = inYData.size();
    scatterCoordinates = new ArrayList<Coordinate>();
    lineCoordinates = new ArrayList<Coordinate>();
    double finalX = 0.0;
    double dub = 0.0;
    for(int i = 0; i < min ; i++) {
      scatterCoordinates.add(new Coordinate(dub, inYData.get(i)));
      lineCoordinates.add(new Coordinate(dub, Regressor.function(dub, slope, yIntercept)));
      finalX = dub;
      dub += 1.0;
    }
    finalX += 2.0;
    int maxY = Grapher.findMax(scatterCoordinates, false) + 10;
    int minY = Grapher.findMin(scatterCoordinates, false) - 10;
    if(slope < 0.0) {
      while( Regressor.function(finalX, slope, yIntercept) > minY) {
        lineCoordinates.add(new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
        finalX+= 2.0;
      }
      lineCoordinates.add(new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
      lineCoordinates.add(0 , new Coordinate(0, yIntercept));
      maxY = Grapher.findMax(scatterCoordinates, false) + 10;
      minY = Grapher.findMin(scatterCoordinates, false) - 10;
      finalX = 0.0;
      while(Regressor.function(finalX, slope, yIntercept) < maxY ) {
        lineCoordinates.add(0 , new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
        finalX -= 2.0;
      }
      lineCoordinates.add(0 , new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
    } else {
      while(Regressor.function(finalX, slope, yIntercept) < maxY && Regressor.function(finalX, slope, yIntercept) > minY) {
        lineCoordinates.add(new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
        finalX+= 2.0;
      }
      lineCoordinates.add(new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
      lineCoordinates.add(0 , new Coordinate(0, yIntercept));
      maxY = Grapher.findMax(scatterCoordinates, false) + 10;
      minY = Grapher.findMin(scatterCoordinates, false) - 10;
      finalX = 0.0;
      while(Regressor.function(finalX, slope, yIntercept) > minY ) {
        lineCoordinates.add(0 , new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
        finalX -= 2.0;
      }
      lineCoordinates.add(0 , new Coordinate(finalX, Regressor.function(finalX, slope, yIntercept)));
    }
  }


  public static void main(String[] args) {
    String in1 = args[0];
    String title = "";
    // String in2 = args[1];
    if(in1.indexOf(".txt") == -1) {
      title = in1;
      in1 += ".txt";
    } else {
      int i = in1.indexOf(".txt");
      title = in1.substring(0,i);
    }
    LineCreator lc = new LineCreator(in1, title);
  }

}
