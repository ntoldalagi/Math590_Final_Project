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
    private ArrayList<Coordinate> lineCoords;
    private ArrayList<Coordinate> scatterCoords;

    public Grapher() {
    }
    public Grapher(String s) {
      title = s;
      launch();
    }

    @Override public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);
        // final ScatterChart<Number,Number> scatterGraph = new
        //     ScatterChart<Number,Number>(xAxis,yAxis);
        final LineChart<Number, Number> lineGraph = new
            LineChart<Number, Number>(xAxis, yAxis);
        xAxis.setLabel("Time in Days");
        yAxis.setLabel("Prices");
        lineGraph.setTitle(title);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(title + " prices");
        series1.getData().add(new XYChart.Data(4.2, 193.2));
        series1.getData().add(new XYChart.Data(2.2, 393.2));
        series1.getData().add(new XYChart.Data(6.2, 119.2));
        series1.getData().add(new XYChart.Data(1.2, 180.2));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Regression Line");
        series2.getData().add(new XYChart.Data(5.2, 229.2));
        series2.getData().add(new XYChart.Data(9.2, 429.2));
        series2.getData().add(new XYChart.Data(7.2, 219.2));
        series2.getData().add(new XYChart.Data(3.2, 179.2));

        lineGraph.setAnimated(false);
        lineGraph.setCreateSymbols(true);

        lineGraph.getData().addAll(series1, series2);
        Scene scene  = new Scene(lineGraph, 500, 400);
        scene.getStylesheets().add(getClass().getResource("chartStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
      Grapher g = new Grapher("Tesla");
    }
}
