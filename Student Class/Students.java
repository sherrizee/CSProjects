/*
Students UML

Variables:
- total : int
- counter : int
- average : double
- size : (final) int
- grade : char
- scores : int []
- finalScores : int []

Constructors:
+ Students()
+ Students(String, int, String, Address) 
+ Students(int [], char)
+ Students(Students);

Methods:
+ setScores(int []) : void
+ getScores(int) : int
+ getAverage() : double
+ getGrade() : char
*/

package FinalProject;

import java.util.ArrayList;

public class Students extends PersonalInfo{
    private int total;
    private int counter;
    private final int size = 8;
    
    private int [] scores = new int[size];
    private double average;
    private char grade;
    private int [] finalScores = new int[6];
    
    Students(){}
    
    Students(String fullName, int ID, String birthDate, Address a)
    {
        super(fullName, ID, birthDate, a);
    }
    
    Students(int [] scores, char grade) {
        setScores(scores);
        average = 0.0;
        grade = 'I';
        total = 0;
        counter = 0;
    }
    
    Students(Students s) {
        average = s.getAverage();
        grade = s.getGrade();
        s.getScores(counter);
    }
    
    public void setScores(int [] setScores) {
        counter = 0;
        total = 0;
        
        for (int i = 0; i < size; i++)
            if (setScores[i] <= 100 && setScores[i] >= 0) {
                scores[counter] = setScores[i];
                counter++;
            }
        
        //sorting the array of valid numbers
        int temp;
        boolean status;
        do {
            status = false;
            for (int i = 0; i < (counter-1); i++)
                if (scores[i] > scores[i+1]) {
                    temp = scores[i];
                    scores[i] = scores[i+1];
                    scores[i+1] = temp;
                    status = true;
                }
        } while (status);
        
        if (counter > 6) {
            for (int i = (counter - 6); i < counter; i++) {
                total += scores[i];
                finalScores[i-counter+6] = scores[i];
            }
        }
        else if (counter == 6) {
            for (int i = 0; i < counter; i++) {
                total += scores[i];
                finalScores[i] = scores[i];
            }
        }
        else if (counter < 6)
            total = 0;
                    
    }
    
    public int getScores(int size) {
        return finalScores[size];       
    }
    
    public double getAverage() {

        if (total > 0)
            average = total / 6;
        else if (total <= 0)
            average = 0;
        
        return average;
    }
    
    public char getGrade() {
        if (counter < 6)
            grade = 'I';
        else if (average >= 90)
            grade = 'A';
        else if (average >= 80)
            grade = 'B';
        else if (average >= 70)
            grade = 'C';
        else if (average >= 60)
            grade = 'D';
        else if (average >= 0)
            grade = 'F';
    
        return grade;
    }
}
