/*
Course UML

Variables:
- courseName : String
- courseID : String
- location : String
- studentArray : ArrayList of Students
- classAverage : double
- size : integer
- total : double

Constructors:
+ Course()
+ Course(String, String, String, Students, int, double) 
+ Course(Course);

Methods:
+ toString() : String
+ setCourseName(String) : void
+ setCourseID(String) : void
+ setLocation(String) : void
+ setStudent(Students, int) : void
+ setSwapStudents(Students, Students, int) : void
+ setClassAverage(double, int) : void
+ getCourseName() : String
+ getCourseID() : String
+ getLocation() : String
+ getStudent(int) : Students
+ getClassAverage(int) : double
+ removeStudent(int) : void
*/

package FinalProject;

import java.util.ArrayList;

public class Course{
    private String courseName;
    private String courseID;
    private String location;
    private ArrayList<Students> studentArray = new ArrayList<Students>(20);
    private double classAverage;
    private int size;
    private double total;
    
    
    Course() {}
    
    Course(String courseName, String courseID, String location, int size, Students s, double scores) {
        setCourseName(courseName);
        setCourseID(courseID);
        setLocation(location);
        setStudent(s, size);
        setClassAverage(scores, size);
        total = 0;
        size = 0;
        studentArray = new ArrayList<Students>();
    }
    
    Course(Course c) {
        setCourseName(c.getCourseName());
        setCourseID(c.getCourseID());
        setLocation(c.getLocation());
        setStudent(c.getStudent(size), size);
        setClassAverage(c.getClassAverage(size), size);
    }
    
    public String toString(){
        String temp = "blank";
            temp = courseName 
            + "\n" + courseID 
            + "\n" + location;          
        
        return temp;
    }
        
    public void setCourseName(String setCourseName) {
        courseName = setCourseName;
    }
    
    public void setCourseID(String setCourseID) {
        courseID = setCourseID;
    }
    
    public void setLocation(String setLocation) {
        location = setLocation;
    }
    
    public void setStudent(Students s, int size){
        studentArray.add(size, s);
    }
    
    public void setSwapStudents(Students s1, Students s2, int size) {
        Students temp = s1;
        studentArray.set(size, s2);
        studentArray.set((size+1), temp);
    }
       
    public void setClassAverage(double scores, int size) {
        if (size == 0)
            total = 0;
        total += scores;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public String getCourseID() {
        return courseID;
    }
    
    public String getLocation() {
        return location;
    }
    
    public Students getStudent(int index) {
        return studentArray.get(index);
    }
    
    public double getClassAverage(int size) {
        classAverage = total / size;
        return classAverage;
    }
    
    public void removeStudent(int size) {
        studentArray.remove(size);
    }

   
}
