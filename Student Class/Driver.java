package FinalProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Driver {
    
    public static Scanner scnr = new Scanner(System.in);
    public static ArrayList<PersonalInfo> array = new ArrayList<PersonalInfo>();
    
    public static void main(String[] args) throws IOException{
        
        JOptionPane.showMessageDialog(null, "Part 1");
        System.out.println("Part 1");
        ReadList();
        Sort();
        PrintTable();
        FindStudent();
                
        int size;
        
        JOptionPane.showMessageDialog(null, "Part 2");
        System.out.println("Part 2");
        Course c = new Course();
        size = ReadList2(c);
        PrintTable2(c, size);
        Menu(c, size);   
        
    }
    
    public static void ReadList() throws IOException{
        String fileName;
        int index = 0;
        //System.out.print("Enter input file name: ");
        //fileName = scnr.nextLine();
        fileName = JOptionPane.showInputDialog("Enter input file name: ");
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            
            Address a = new Address();
            PersonalInfo p = new PersonalInfo();
            
            p.setFullName(input.nextLine());
            p.setID(input.nextInt());
            p.setBirthDate(input.next());
            input.nextLine();
            a.setStreetNumber(input.nextInt());
            a.setStreetName(input.next());
            input.nextLine();
            a.setZip(input.nextInt());
            a.setState(input.next());
            a.setCity(input.nextLine());
            p.setAddress(a);
            
            array.add(index, p);
           
            index++;
        }
    }
    
    public static void Sort() 
    {
	boolean swapped;
        PersonalInfo temp1;
        int size = array.size();
        String [] lastName = new String[size];
        String sTemp;
        
        //array of Strings for last names to compare
        for(int i = 0; i < size; i++){
            sTemp = array.get(i).getFullName();
            lastName[i] = sTemp.substring(sTemp.lastIndexOf(" ")+1);
        }
     
        do 
        {
            swapped = false;
            size--;
            for (int i = 0; i < size; i++)
            {
		if (lastName[i].compareTo(lastName[i+1]) > 0)
		{
                    temp1 = new PersonalInfo(array.get(i));
                    array.set(i,array.get(i+1));
                    array.set(i+1, temp1);
                    
                   
                    //swap the string array of last names
                    sTemp = lastName[i];
                    lastName[i] = lastName[i+1];
                    lastName[i+1] = sTemp;
                    
                    swapped = true;
		}
            }
        } while (swapped);
    }
    
    public static void PrintTable() throws IOException{
        FileWriter fileOut;
        PrintWriter out;
        String fileName;
        Scanner input = new Scanner(System.in);    
        String firstName, lastName, sTemp;
        int ID;
//        System.out.print("Enter output file name: ");
//        fileName = input.nextLine();
        fileName = JOptionPane.showInputDialog("Enter output file name: ");
        System.out.println("");
        fileOut = new FileWriter(fileName);
        out = new PrintWriter(fileOut);
        System.out.printf("%-25s%-22s%-15s\n", "NAME", "ID", " CITY");
        out.printf("%-25s%-22s%-15s\n", "NAME", "ID", " CITY");
        for (int i = 0; i < array.size(); i++) {
            sTemp = array.get(i).getFullName();
            firstName = sTemp.substring(0, sTemp.lastIndexOf(" "));
            lastName = sTemp.substring(sTemp.lastIndexOf(" ")+1);
            sTemp = lastName + ", " + firstName;
            ID = array.get(i).getID() % 10000;
            System.out.printf("%-25s" + "***-**-" + "%-15d%-15s\n", sTemp, ID, array.get(i).getAddress().getCity());
            out.printf("%-25s" + "***-**-" + "%-15d%-15s\n", sTemp, ID, array.get(i).getAddress().getCity());
        }
        out.println();
        out.close();
        System.out.println("");
        
    }
    
    public static void Search(String lastName) {
        String lastNameTemp, fullNameTemp;
        int counter = 0;
        for (int i = 0; i < array.size(); i++) {
            fullNameTemp = array.get(i).getFullName();
            lastNameTemp = fullNameTemp.substring(fullNameTemp.lastIndexOf(" ")+1);
            if (lastNameTemp.toUpperCase().contains(lastName.toUpperCase())) {
                System.out.println(array.get(i).getFullName() + " is located at index " + i);
                counter++;
            }
        }
        System.out.println("");
        if (counter == 0)
            System.out.println("--- '" + lastName + "' is not a vaild last name on the list ---");
        else
            System.out.println("--- Search completed for keyword '" + lastName + "' ---");
        
        System.out.println("");
    
    }
    
    public static void FindStudent() {
        String lastName;
        System.out.print("Enter student last name (partial or full) to search for index: ");
        lastName = scnr.nextLine();
        Search(lastName);
    }
    
    //part2 methods
    
    public static void Menu(Course c, int size)throws IOException {
        String choice = "";
        int choice2 = 0;
        do {
            System.out.println("");
            System.out.println("Menu: \n"
                             + "A. Print course content to a file\n"
                             + "B. Sort the list of students alphabetically and descending student average scores\n"
                             + "C. Print list of students registered in a course\n"
                             + "D. Search in a list of students to print all details of a specific student\n"
                             + "E. Add a new student to the list\n"
                             + "F. Delete a student from the lsit\n"
                             + "G. Edit student information (name and test scores)\n"
                             + "H. Exit");
            System.out.print("Enter a letter: ");
            choice = scnr.next().toUpperCase();
            System.out.println("");
            switch(choice) {
                case "A":
                    PrintToFile(c, size);
                    break;
                case "B":
                    System.out.println("List of sorting methods: ");
                    System.out.println("1. Alphabetical sort (based on last name)");
                    System.out.println("2. Decending sort (based on student average)");
                    System.out.print("Please select a choice: ");
                    choice2 = scnr.nextInt();
                    while (choice2 > 2 || choice2 < 1) {
                        System.out.println("Invalid choice, must be either 1 or 2!");
                        System.out.print("Please select a choice: ");
                        choice2 = scnr.nextInt();
                    }
                    if (choice2 == 1)
                        SortAlphabetical(c, size);
                    else if (choice2 == 2)
                        SortDecending(c, size);                      
                    break;
                case "C":
                    PrintStudentsFromCourse(c, size);
                    break;
                case "D":
                    Search2(c, size);
                    break;
                case "E":
                    size = AddStudent(c, size);
                    break;
                case "F":
                    size = DeleteStudent(c, size);
                    break;
                case "G":
                    EditStudentInfo(c, size);
                    break;
                case "H":
                    System.out.println("--- Have a nice day!!(: ---");
                    break;
                default: 
                    System.out.println("--- Invalid choice! Please reselect a letter (A-H) ---");
                    break;            
            }
        } while (!(choice.equalsIgnoreCase("H")));
        
    }
    
    public static int ReadList2(Course c) throws IOException {
        String fileName = "part2.txt";
        int index = 0;
        int [] tempArray = new int[8];
//        System.out.print("Enter input 2nd file name: ");
//        fileName = scnr.nextLine();
        fileName = JOptionPane.showInputDialog("Enter input 2nd file name: ");
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        c.setCourseName(input.nextLine());
        c.setCourseID(input.nextLine());
        c.setLocation(input.nextLine());
        while (input.hasNext()) {
            
            Students s = new Students();
            Address a = new Address();
            
            s.setFullName(input.nextLine());
            s.setID(input.nextInt());
            s.setBirthDate(input.next());
            for (int i = 0; i < 8; i++){
                tempArray[i] = input.nextInt();
            }
            s.setScores(tempArray);
            input.nextLine();
            a.setStreetNumber(input.nextInt());
            
            a.setStreetName(input.nextLine());
            a.setZip(input.nextInt());
            a.setState(input.next());
            a.setCity(input.nextLine());
            
            s.setAddress(a);
            
            c.setStudent(s, index);
           
            index++;
        }
        
        return index;
    }
        
    public static void PrintTable2(Course c, int size) throws IOException {
  
        String firstName = "", lastName = "", sTemp = "";
        int ID = 0;
        System.out.println("");
        System.out.printf("%-24s%-23s%-20s%-20s\n", "NAME", " ID", "AVERAGE", "GRADE");
        for (int i = 0; i < size; i++) {
            sTemp = c.getStudent(i).getFullName();
            firstName = sTemp.substring(0, sTemp.lastIndexOf(" "));
            lastName = sTemp.substring(sTemp.lastIndexOf(" ")+1);
            sTemp = lastName + ", " + firstName;
            ID = c.getStudent(i).getID() % 10000;
            System.out.printf("%-25s" + "***-**-" + "%-15d%-20.2f%-20s\n", sTemp, ID, c.getStudent(i).getAverage(), c.getStudent(i).getGrade());
        }
    }
    
    public static void PrintToFile(Course c, int size) throws IOException {
        FileWriter fileOut;
        PrintWriter out;
        String fileName = "out.txt";
        Scanner input = new Scanner(System.in);    
        String firstName, lastName, sTemp;
        int ID;
//        System.out.print("Enter output file name: ");
//        fileName = input.nextLine();
        fileName = JOptionPane.showInputDialog("Enter output file name: ");
        fileOut = new FileWriter(fileName);
        out = new PrintWriter(fileOut);
        out.print(c.toString());
        out.printf("%-26s%-22s%-23s%-20s%-20s\n", "NAME", "CITY", " ID", "AVERAGE", "GRADE");
        for (int i = 0; i < size; i++) {
            sTemp = c.getStudent(i).getFullName();
            firstName = sTemp.substring(0, sTemp.lastIndexOf(" "));
            lastName = sTemp.substring(sTemp.lastIndexOf(" ")+1);
            sTemp = lastName + ", " + firstName;
            ID = c.getStudent(i).getID() % 10000;
            out.printf("%-25s%-24s" + "***-**-" + "%-15d%-20.2f%-20s\n", sTemp, c.getStudent(i).getAddress().getCity(), ID, c.getStudent(i).getAverage(), c.getStudent(i).getGrade());
        }
        int validGrades = 0;
        for (int i = 0; i < size; i++) {
            if (c.getStudent(i).getGrade() != 'I')
                validGrades++;
        }
        for (int i = 0; i < validGrades; i++)
            c.setClassAverage(c.getStudent(i).getAverage(), i);
        out.printf("\nClass average for " + validGrades + " students: %.2f",c.getClassAverage(validGrades));
        out.println("\n");
        out.close();
        System.out.println("--- Course content has been added to file ---");
    }

    public static void SortAlphabetical(Course c, int size)throws IOException {
        boolean swapped;
        int counter = size;
        
        String [] lastName = new String[size];
        String sTemp;        
        
        //array of Strings for last names to compare
        for(int i = 0; i < size; i++){
            sTemp = c.getStudent(i).getFullName();
            lastName[i] = sTemp.substring(sTemp.lastIndexOf(" ")+1);
        }
        
        do 
        {
            swapped = false;
            counter--;
            for (int i = 0; i < counter; i++)
            {
		if (lastName[i].compareTo(lastName[i+1]) > 0)
		{
                    c.setSwapStudents(c.getStudent(i), c.getStudent(i+1), i);
                                     
                    //swap the string array of last names
                    sTemp = lastName[i];
                    lastName[i] = lastName[i+1];
                    lastName[i+1] = sTemp;
                    
                    swapped = true;
		}
            }
        } while (swapped);
        PrintTable2(c, size);
        System.out.println("");
        System.out.println("--- List of students has been sorted alphabetically ---");
    }
    
    public static void SortDecending(Course c, int size)throws IOException {
        boolean swapped;
        int counter = size;
        
        do 
        {
            swapped = false;
            counter--;
            for (int i = 0; i < counter; i++)
            {
		if (c.getStudent(i).getAverage() < c.getStudent(i+1).getAverage())
		{
                    c.setSwapStudents(c.getStudent(i), c.getStudent(i+1), i);
//                                                    
                    swapped = true;
		}
            }
        } while (swapped);
        PrintTable2(c, size);
        System.out.println("");
        System.out.println("--- List of students has been sorted in decending order of averages ---");
    }
    
    public static void PrintStudentsFromCourse(Course c, int size)throws IOException {
        String courseName;
        
        System.out.print("Enter in course name or keyword: ");
        scnr.nextLine(); //CRLF bug where something automatically reads so I need to skip a line
        courseName = scnr.nextLine();
        System.out.println("");
        
        if (c.getCourseName().toUpperCase().contains(courseName.toUpperCase())) {
            System.out.print(c.toString());
            PrintTable2(c, size);
            System.out.println("");
            System.out.println("--- List of students in the course has been printed ---");
        }
        else {
            System.out.println("");
            System.out.println("--- '" + courseName + "' is not a vaild course name ---\n");
        }
    }

    public static void Search2(Course c, int size) {
        String lastName, fullNameTemp, firstName, temp, scoresTemp = "";
        int counter = 0, ID;
        System.out.print("Enter student last name (partial or full): ");
        temp = scnr.next();
        System.out.println("");
        System.out.printf("%-24s%-23s%-20s%-20s%-20s\n", "NAME", " ID", "AVERAGE", "GRADE", "TEST SCORES");
        for (int i = 0; i < size; i++) {
            fullNameTemp = c.getStudent(i).getFullName();
            firstName = fullNameTemp.substring(0, fullNameTemp.lastIndexOf(" "));
            lastName = fullNameTemp.substring(fullNameTemp.lastIndexOf(" ")+1);
            scoresTemp = "";
            
            if (lastName.toUpperCase().contains(temp.toUpperCase())) {
            fullNameTemp = lastName + ", " + firstName;
            ID = c.getStudent(i).getID() % 10000;
            for (int k = 0; k < size; k++) {
                scoresTemp += c.getStudent(i).getScores(k) + " ";
            }
            System.out.printf("%-25s" + "***-**-" + "%-15d%-20.2f%-20s%-20s\n", fullNameTemp, ID, c.getStudent(i).getAverage(), c.getStudent(i).getGrade(), scoresTemp);
            counter++;
            }  
        }
        if (counter == 0) {
            System.out.println("");
            System.out.println("--- '" + temp + "' is not a vaild last name on the list ---\n");
        }
        else {
            System.out.println("");
            System.out.println("--- Search containing the keyword '" + temp + "' has printed all out matching students ---");
        }
    }
    
    public static int AddStudent(Course c, int size) {
        Students s = new Students();
        Address a = new Address();
        int [] tempArray = new int[8];
        System.out.print("Full name (first and last): ");
        scnr.nextLine(); //CRLF bug where something automatically reads so I need to skip a line
        s.setFullName(scnr.nextLine());
        System.out.print("ID (#########): ");
        s.setID(scnr.nextInt());
        System.out.print("Birthdate (MM/DD/YYYY): ");
        s.setBirthDate(scnr.next());
        System.out.println("8 test scores (0-100): ");
        for (int i = 0; i < 8; i++){
            System.out.print((i+1) + ". ");
            tempArray[i] = scnr.nextInt();
        }
        s.setScores(tempArray);
        System.out.print("Street number: ");
        a.setStreetNumber(scnr.nextInt());
        System.out.print("Street Name: ");
        a.setStreetName(scnr.nextLine());
        scnr.nextLine(); //CRLF bug where something automatically reads so I need to skip a line
        System.out.print("Zip code (#####): ");
        a.setZip(scnr.nextInt());
        System.out.print("State: ");
        a.setState(scnr.next());
        System.out.print("City: ");
        scnr.nextLine(); //CRLF bug where something automatically reads so I need to skip a line
        a.setCity(scnr.nextLine());

        s.setAddress(a);

        c.setStudent(s, size);
        
        System.out.println("");
        System.out.println("--- " + c.getStudent(size).getFullName() + " has been added ---");
        
        return (size + 1);
    }
    
    public static int DeleteStudent(Course c, int size){
        String lastName, fullNameTemp = "", temp, choice, name;
        int counter = 0, delete = 0, update = size;
        int [] array = new int[20];
        System.out.print("Enter student last name (partial or full) to delete: ");
        temp = scnr.next();
        for (int i = 0; i < size; i++) {
            fullNameTemp = c.getStudent(i).getFullName();
            lastName = fullNameTemp.substring(fullNameTemp.lastIndexOf(" ")+1);
            
            if (lastName.toUpperCase().contains(temp.toUpperCase())) {
                System.out.println((counter + 1) + ". " + fullNameTemp);
                array[counter] = i;
                counter++;
            }  
        }
        if (counter == 0) {
            System.out.println("");
            System.out.println("--- '" + temp + "' is not a vaild last name on the list ---");
        }
        else if (counter > 1) {
            System.out.print("Please select a number to delete: ");
            delete = scnr.nextInt();
            delete--;
            delete = array[delete];
        }
        else if (counter == 1)
            delete = array[0];
            
        if (counter >= 1){
            name = c.getStudent(delete).getFullName();
            System.out.print("Are you sure you want to delete " + name + "? ('yes' or 'no'): ");
            choice = scnr.next();
            if (choice.equalsIgnoreCase("yes")) {
                c.removeStudent(delete);
                update--;
                System.out.println("");
                System.out.println("-- We have removed " + name + " ---");
            }
            else if (choice.equalsIgnoreCase("no")) {
                System.out.println("");
                System.out.println("--- No worries, we will keep " + name + " ---");
            }
            else {
                System.out.println("");
                System.out.println("--- Invaild answer! ---");
            }
        }
        return update;
    }
    
    public static void EditStudentInfo(Course c, int size) {
        String lastName, fullNameTemp = "", temp, choice, name, newName;
        int counter = 0, edit = 0;
        int [] array = new int[20];
        int [] tempArray = new int[8];
        System.out.print("Enter student last name (partial or full) to edit: ");
        temp = scnr.next();
        for (int i = 0; i < size; i++) {
            fullNameTemp = c.getStudent(i).getFullName();
            lastName = fullNameTemp.substring(fullNameTemp.lastIndexOf(" ")+1);
            
            if (lastName.toUpperCase().contains(temp.toUpperCase())) {
                System.out.println((counter + 1) + ". " + fullNameTemp);
                array[counter] = i;
                counter++;
            }  
        }
        if (counter == 0) {
            System.out.println("");
            System.out.println("--- '" + temp + "' is not a vaild last name on the list ---");
        }
        else if (counter > 1) {
            System.out.print("Please select a number to edit: ");
            edit = scnr.nextInt();
            edit--;
            edit = array[edit];
        }
        else if (counter == 1)
            edit = array[0];
            
        if (counter >= 1){
            name = c.getStudent(edit).getFullName();
            System.out.print("Are you sure you want to edit " + name + "? ('yes' or 'no'): ");
            choice = scnr.next();
            System.out.println("");
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("List of things to edit: ");
                System.out.println("1. Name");
                System.out.println("2. Test Scores");
                System.out.print("Which do you want to edit? (1 or 2): ");
                choice = scnr.next();
                if (choice.equals("1")) {
                    System.out.print("What do you want your new full name to be?: ");
                    scnr.nextLine(); //CRLF bug where something automatically reads so I need to skip a line
                    newName = scnr.nextLine();
                    c.getStudent(edit).setFullName(newName);
                    System.out.println("");
                    System.out.println("-- We have edited the name to now be " + newName + " ---");   
                }
                else if (choice.equals("2")) {
                    System.out.println("8 new test scores: ");
                    for (int i = 0; i < 8; i++){
                        System.out.print((i+1) + ". ");
                        tempArray[i] = scnr.nextInt();
                    }
                    c.getStudent(edit).setScores(tempArray);
                    System.out.println("");
                    System.out.println("-- We have edited the list of scores for " + name + " ---");
                }
                else {
                    System.out.println("");
                    System.out.println("-- Invalid answer! ---");
                }
            }
            else if (choice.equalsIgnoreCase("no")) {
                System.out.println("");
                System.out.println("--- No worries, we will keep " + name + " the same ---");
            }
            else {
                System.out.println("");
                System.out.println("--- Invaild answer! ---");
            }
        } 
    }       
}
