import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Grapher extends Application {
    private static String title = "Unnamed";
    private static ArrayList<Coordinate> lineCoords;
    private static ArrayList<Coordinate> scatterCoords;
    private static String xoriginal = null;
    private String startDate;
    public Grapher() {
    }

    public Grapher(String s, ArrayList<Coordinate> lCoord, ArrayList<Coordinate> sCoord, String start) {
      scatterCoords = sCoord;
      lineCoords = lCoord;
      xoriginal = start;
      title = s;
      launch();
    }

     public void start(Stage stage) {
      int yAxisMax = findMax(scatterCoords, false);
      int xAxisMax = findMax(scatterCoords, true);
      int yAxisMin = findMin(scatterCoords, false);
      int xAxisMin = findMin(scatterCoords, true);
      stage.setTitle(title + " regression graph");
      final NumberAxis xAxis = new NumberAxis(xAxisMin -10, xAxisMax + 10, (xAxisMax - xAxisMin)/10);
      final NumberAxis yAxis = new NumberAxis(yAxisMin -10, yAxisMax + 10, (yAxisMax - yAxisMin)/10);
      final LineChart<Number, Number> lineGraph = new
          LineChart<Number, Number>(xAxis, yAxis);
      xAxis.setLabel("Time in Days after " + xoriginal) ;
      yAxis.setLabel("Prices");
      lineGraph.setTitle(title);
      XYChart.Series series1 = new XYChart.Series();
      series1.setName(title + " prices");
      for(Coordinate c: scatterCoords) {
        series1.getData().add(new XYChart.Data(c.getX(), c.getY()));
      }
      XYChart.Series series2 = new XYChart.Series();
      series2.setName("Regression Line");
      for(Coordinate c: lineCoords) {
        series2.getData().add(new XYChart.Data(c.getX(), c.getY()));
      }
      lineGraph.setAnimated(false);
      lineGraph.setCreateSymbols(true);
      lineGraph.getData().addAll(series1, series2);
      Scene scene  = new Scene(lineGraph, 500, 400);
      scene.getStylesheets().add(getClass().getResource("chartStyle.css").toExternalForm());
      stage.setScene(scene);
      stage.show();
    }

    public static int findMax(ArrayList<Coordinate> arr, boolean var) {
      if(var) {
        int max = (int) arr.get(0).getX();
        for(Coordinate c : arr) {
          if(c.getX() > max) {
            max = (int) c.getX();
          }
        }
        return max;
      } else {
        int max = (int) arr.get(0).getY();
        for(Coordinate c: arr) {
          if(c.getY() > max) {
            max = (int) c.getY();
          }
        }
        return max;
      }
    }

    public static int findMin(ArrayList<Coordinate> arr, boolean var) {
      if(var) { //if Var, gets the x Value
        int min = (int) arr.get(0).getX();
        for(Coordinate c : arr) {
          if(c.getX() < min) {
            min = (int) c.getX();
          }
        }
        return min;
      } else {
        int min = (int) arr.get(0).getY();
        for(Coordinate c: arr) {
          if(c.getY() < min) {
            min = (int) c.getY();
          }
        }
        return min;
      }
    }

    public static void main(String[] args) {
    }
}
