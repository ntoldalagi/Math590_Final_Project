import java.util.ArrayList;

public class Regressor {
  private ArrayList<Double> xVals;
  private ArrayList<Double> yVals;
  private ArrayList<Coordinate> coords;
  private double slope;

  public Regressor(ArrayList<Double> x, ArrayList<Double> y) {
    xVals = x;
    yVals = y;
    coords = createCoords();
    System.out.println(calculateCost(3.0, coords));
  }


  public ArrayList<Coordinate> createCoords() {
    ArrayList<Coordinate> out = new ArrayList<Coordinate>();
    int min = Math.min(xVals.size(), yVals.size());
    for(int i = 0; i< min; i++) {
      out.add(new Coordinate(xVals.get(i), yVals.get(i)));
    }

    return out;
  }

  public double calculateCost(double m, ArrayList<Coordinate> coords) {
    int size = coords.size();
    double sum = 0.0;
    for(Coordinate c: coords) {
      sum += c.getY() - function(c.getX());
    }
    double average = sum / size;
    return average;
  }

  public double function(double x) {
    return x*slope;
  }
}
