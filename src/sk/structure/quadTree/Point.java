package sk.structure.quadTree;

/**
 *
 * @author Tomas Hreben
 */
public class Point {
    
    /**
     * X coordinate for point
     */
    private double x_coordinate;
    /**
     * Y cootdinate for point
     */
    private double y_coordinate;
    /**
     * extra information about point
     */
    private String information;
    
    public Point(double x_coordinate, double y_coordinate, String information) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.information = information;
    }

    public double getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(double x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public double getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(double y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
    
    
}
