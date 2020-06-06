/*
PersonalInfo UML

Variables:
- fullName : String
- ID : int
- birthDate : String
- personAddress : Address

Constructors:
+ PersonalInfo()
+ PersonalInfo(String, int, String, Address) 
+ PersonalInfo(PersonalInfo);

Methods:
+ toString() : String
+ setFullName(String) : void
+ setID(int) : void
+ setBirthDate(String) : void
+ setPersonAddress(Address) : void
+ getFullName() : String
+ getID() : int
+ getBirthDate() : String
+ getAddress() : Address
*/

package FinalProject;

public class PersonalInfo {
    private String fullName;
    private int ID;
    private String birthDate;
    private Address personAddress;
    
    PersonalInfo(){}
    
    PersonalInfo(String fullName, int ID, String birthDate, Address a) {
        setFullName(fullName);
        setID(ID);
        setBirthDate(birthDate);
        setAddress(new Address (a));
    }
    
    PersonalInfo(PersonalInfo p) {
        setFullName(p.getFullName());
        setID(p.getID());
        setBirthDate(p.getBirthDate());
        setAddress(new Address (p.getAddress())); //pass thru as a new reference for each object/class
    }
    
    public String toString(){
        String temp;
        temp = fullName 
             + "\n" + ID + " " + birthDate 
             + "\n" + personAddress;
        return temp;
    }
    
    public void setFullName(String setFullName){
        fullName = setFullName;
    }
    
    public void setID(int setID){
        if (setID >= 100000000 && setID < 999999999)
            ID = setID;
    }
    
    public void setBirthDate(String setBirthDate){
        birthDate = setBirthDate;
    }
    
    public void setAddress(Address a){
        personAddress = new Address(a);
    }
    
    public String getFullName (){
        return fullName;
    }
    
    public int getID( ){
        return ID;
    }
    
    public String getBirthDate(){
        return birthDate;
    }
    
    public Address getAddress(){
        return (new Address (personAddress));
    }
    
}
