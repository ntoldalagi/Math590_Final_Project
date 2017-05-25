import java.util.ArrayList;

public class Regressor {
  private ArrayList<Double> xVals;
  private ArrayList<Double> yVals;
  private ArrayList<Coordinate> coords;
  private double finalSlope;
  private double yIntercept;

  public Regressor(ArrayList<Double> x, ArrayList<Double> y) {
    xVals = x;
    yVals = y;
    coords = createCoords();
    finalSlope = findMin(coords);
  }

  public double getFinalSlope() {
    return finalSlope;
  }

  public double getFinalIntercept() {
    return yIntercept;
  }

  public ArrayList<Coordinate> createCoords() {
    ArrayList<Coordinate> out = new ArrayList<Coordinate>();
    int min = Math.min(xVals.size(), yVals.size());
    for(int i = 0; i< min; i++) {
      out.add(new Coordinate(xVals.get(i), yVals.get(i)));
    }

    return out;
  }

  public double calculateFirstCostSlope(double m, double y, ArrayList<Coordinate> coords) {
    int size = coords.size();
    double sum = 0.0;
    for(Coordinate c: coords) {
      sum += (c.getY() - function(c.getX(), m, y)) * c.getX();
    }
    double average = sum / size;
    return average ;
  }

  public double calculateSecondCostSlope(double m, double y, ArrayList<Coordinate> coords) {
    int size = coords.size();
    double sum = 0.0;
    for(Coordinate c: coords) {
      sum += (c.getY() - function(c.getX(), m, y));
    }
    double average = sum / size;
    return average;
  }

  public double calculateCost(double m, double y, ArrayList<Coordinate> coords) {
    int size = coords.size();
    double sum = 0.0;
    for(Coordinate c: coords) {
      sum += Math.pow(c.getY()- function(c.getX(), m, y), 2);
    }
    double average = sum / (size*2);
    return average;
  }

  public static double function(double x, double m, double y) {
    return x*m + y;
  }


  public double findMin(ArrayList<Coordinate> coords) {
    double y_change = coords.get(coords.size()-1).getY() - coords.get(0).getY();
    double x_Change =  (coords.get(coords.size()-1).getX() - coords.get(0).getX());
    double y_Cept = 0.0;
    if(coords.get(0).getX() == 0.0) {
      y_Cept = coords.get(1).getY();
    } else {
      y_Cept = coords.get(0).getY();
    }
    double slope = y_change/x_Change;
    double factor1 = .000001;
    double factor2 = .000001;
    double costSlope = calculateFirstCostSlope(slope, y_Cept, coords);
    System.out.println("Initial costSlope: " + costSlope);
    double costSlope2 = calculateSecondCostSlope(slope, y_Cept, coords);

    int i =0;
    while((costSlope < .00001) /*|| i< 100000*/) {
      // System.out.println(costSlope);
      y_Cept = y_Cept + (.01) * calculateSecondCostSlope(slope, y_Cept, coords);
      slope = slope + (.00001) * calculateFirstCostSlope(slope, y_Cept, coords);
      costSlope = calculateFirstCostSlope(slope, y_Cept, coords);
      costSlope2 = calculateSecondCostSlope(slope, y_Cept, coords);
      System.out.println("Final: costSlope: " + costSlope);
      System.out.println("Second: " + costSlope2);
      // cost1 = calculateCost(slope - .01, coords);
      // cost2 = calculateCost(slope + .01, coords);
      i++;
    }
    System.out.println("Slope: " + slope + "y_Cept: " + y_Cept);
    yIntercept = y_Cept;
    return slope;
  }
}
