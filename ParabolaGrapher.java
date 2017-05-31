import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ParabolaGrapher extends Application {
    private static String title = "Unnamed";
    private static ArrayList<Coordinate> lineCoords;
    private static ArrayList<Coordinate> scatterCoords;
    private static String xoriginal = null;
    private static ArrayList<Coordinate> coordslist;
    private String startDate;
    public ParabolaGrapher() {
    }

    public ParabolaGrapher(String s, ArrayList<Coordinate> lCoord, ArrayList<Coordinate> sCoord, String start, ArrayList<Coordinate> list) {
      scatterCoords = sCoord;
      lineCoords = lCoord;
      xoriginal = start;
      title = s;
      coordslist = list;
      launch();
    }

     public void start(Stage stage) {
      stage.setTitle(title + " cost function graph");
      System.out.println(coordslist);
      int yAxisMax = findMax(coordslist, false);
      int xAxisMax = 500;
      int yAxisMin = findMin(coordslist, false);
      int xAxisMin = findMin(coordslist, true);
      final NumberAxis xAxis1 = new NumberAxis(xAxisMin -10, xAxisMax + 10, (xAxisMax - xAxisMin)/10);
      final NumberAxis yAxis1 = new NumberAxis(yAxisMin -10, yAxisMax + 10, (yAxisMax - yAxisMin)/10);
      xAxis1.setLabel("Iterations");
      yAxis1.setLabel("Cost ($)");
      final ScatterChart<Number, Number> scatterChart = new
        ScatterChart<Number, Number>(xAxis1, yAxis1);
      scatterChart.setTitle("Cost Function");
      XYChart.Series series3 = new XYChart.Series();
      series3.setName("Cost of the Trendline");
      // System.out.println("C: " + coordslist);
      for(Coordinate c: coordslist) {
        series3.getData().add(new XYChart.Data(c.getX(), c.getY()));
      }
      scatterChart.setAnimated(false);
      scatterChart.getData().addAll(series3);
      Scene scene1  = new Scene(scatterChart, 500, 400);
      stage.setScene(scene1);
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
