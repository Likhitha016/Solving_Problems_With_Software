package Week1;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    //finds number of points in the shape
    public int getNumPoints (Shape s) {
    	int numpoints=0;
    	for(Point p:s.getPoints()) {
    		numpoints++;
    	}
        return numpoints;
    }
    //finds the average length
    public double getAverageLength(Shape s) {
    	double len = getPerimeter(s);
        double no_of_sides = getNumPoints(s);
        double avglength = len/no_of_sides;
        return avglength;
    }
    //gets the largest side of the shape
    public double getLargestSide(Shape s) {
        double largestside=0.0;
        //find the last point added to the shape
        Point last = s.getLastPoint();
        for(Point p : s.getPoints()){
            //finds the distance to the point p and last point added to the shape
            double d=last.distance(p);
            if(d>largestside) {
                largestside=d;
            }
            //change the last point every time to get largest side
            last=p;
        }
        return largestside;
    }
    //gets largest x-coordinate of all points added to the shape
    public double getLargestX(Shape s) {
    	Point last = s.getLastPoint();
    	//gets x-coordinate of last point added to the shape
        int x_coordinate = last.getX();
        double largestX =x_coordinate;
        for(Point p : s.getPoints()){
            int new_x_coordinate=p.getX();
            if(new_x_coordinate > largestX) {
                largestX = new_x_coordinate;
            }
        }
        return largestX;
    }
    public double getLargestPerimeterMultipleFiles() {
       //Directory resource can select multiple files and then iterates over these files
    	DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        //for each selected file finds the perimeter and find largest perimeter
        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perimeter = getPerimeter(shape);
            if(perimeter > largestPerimeter) {
            	largestPerimeter = perimeter;
            }
        }
        return largestPerimeter;
    }
    //finds the file with largest perimeter
    public File getFileWithLargestPerimeter() {
    	DirectoryResource dr = new DirectoryResource();
    	File largestFile = null;
        double largestPerimeter = 0.0;
        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perimeter = getPerimeter(shape);
            if(perimeter > largestPerimeter) {
            	largestPerimeter = perimeter;
            	largestFile=f;
            }
        }
        return largestFile;
    }
    //tester method to test methods
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = "+length);
        int numPoints = getNumPoints(s);
        System.out.println("Number of Points = "+numPoints);
        double averageLength = getAverageLength(s);
        System.out.println("Average Length = "+averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("Largest side = "+largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X value in all points = "+largestX);
    }
    
    public void testPerimeterMultipleFiles() {
    	double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is = "+largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
    	File file = getFileWithLargestPerimeter();
    	String fname=file.getName();
        System.out.println("File with largest Perimeter is : "+fname);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double perimeter = getPerimeter(triangle);
        System.out.println("perimeter = "+perimeter);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
       PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
       pr. testPerimeter();
    }
}
