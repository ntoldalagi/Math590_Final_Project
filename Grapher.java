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

    public Grapher() {
    }

    public Grapher(String s, ArrayList<Coordinate> lCoord, ArrayList<Coordinate> sCoord) {
      scatterCoords = sCoord;
      lineCoords = lCoord;
      title = s;
      launch();
    }

     public void start(Stage stage) {
      System.out.println("SHOULD RUN SECOND");
      int yAxisMax = 50;
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 40, 4);
        final NumberAxis yAxis = new NumberAxis(0, yAxisMax, 10);
        final LineChart<Number, Number> lineGraph = new
            LineChart<Number, Number>(xAxis, yAxis);
        xAxis.setLabel("Time in Days");
        yAxis.setLabel("Prices");
        lineGraph.setTitle(title);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(title + " prices");

        for(Coordinate c: scatterCoords) {
          series1.getData().add(new XYChart.Data(c.getX(), c.getY()));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Regression Line");
        // series2.getData().add(new XYChart.Data(5.2, 229.2));
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


    public static void main(String[] args) {
    }
}
