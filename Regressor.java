import java.util.ArrayList;

public class Regressor {
  private ArrayList<Double> xVals;
  private ArrayList<Double> yVals;
  private ArrayList<Coordinate> coords;
  private double finalSlope;

  public Regressor(ArrayList<Double> x, ArrayList<Double> y) {
    xVals = x;
    yVals = y;
    coords = createCoords();
    finalSlope = findMin(coords);
    // System.out.println(findMin(coords));
  }

  public double getFinalSlope() {
    return finalSlope;
  }

  public ArrayList<Coordinate> createCoords() {
    ArrayList<Coordinate> out = new ArrayList<Coordinate>();
    int min = Math.min(xVals.size(), yVals.size());
    for(int i = 0; i< min; i++) {
      out.add(new Coordinate(xVals.get(i), yVals.get(i)));
    }

    return out;
  }

  public double calculateCostSlope(double m, ArrayList<Coordinate> coords) {
    int size = coords.size();
    double sum = 0.0;
    for(Coordinate c: coords) {
      sum += (c.getY() - function(c.getX(), m));
    }
    double average = sum / size;
    return average * m;
  }

  public double calculateCost(double m, ArrayList<Coordinate> coords) {
    int size = coords.size();
    double sum = 0.0;
    for(Coordinate c: coords) {
      sum += Math.pow(c.getY()- function(c.getX(), m), 2);
    }
    double average = sum / (size*2);
    return average;
  }

  public static double function(double x, double m) {
    return x*m;
  }

  public double findMin(ArrayList<Coordinate> coords) {
    double y_change = coords.get(coords.size()-1).getY() - coords.get(0).getY();
    double x_Change =  (coords.get(coords.size()-1).getX() - coords.get(0).getX());
    double slope = y_change/x_Change;
    double factor = .000001;
    double costSlope = calculateCostSlope(slope, coords);
    System.out.println("Initial costSlope: " + costSlope);
    double costSlope1 = calculateCostSlope(slope + 1, coords);

    if(costSlope < 0.0) {
      System.out.println("dasfak");
      factor *= -1;
    }
    // cost1 = calculateCost(slope - .01, coords);
    // double cost2 = calculateCost(slope + .01, coords);
    // // System.out.println("djaflk;d");
    // // System.out.println(cost1-cost2);
    // boolean ok =  true;
    // while(ok ||(cost1 - cost2 > .0001) || (cost2 - cost1 < -0.0001)) {
    //   System.out.println(slope);
    //   cost1 = calculateCost(slope, coords);
    //   slope += factor;
    //   cost2 = calculateCost(slope, coords);
    //   if(cost1 < cost2) {
    //     factor *= -1;
    //     factor /= 2;
    //     double temp = cost1;
    //     cost1 = cost2;
    //     cost2 = temp;
    //     ok = false;
    //   }
    // }
    while(costSlope < .00001) {
      // System.out.println(costSlope);
      slope += factor;
      costSlope = calculateCostSlope(slope, coords);
      // cost1 = calculateCost(slope - .01, coords);
      // cost2 = calculateCost(slope + .01, coords);
    }
    System.out.println("Slope: " + slope);
    System.out.println("Final: costSlope: " + costSlope);
    return slope;
  }
}
