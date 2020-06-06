/*
Address UML

Variables:
= streetNumber : int
- streetName : String
- city : String
= state : String
- zip : int

Constructors:
+ Address()
+ Address(String, String, int, int, String) 
+ Addres(Address);

Methods:
+ toString() : String
+ setStreetNumber(int) : void
+ seyStreetName(String) : void
+ setCity(String) : void
+ setState(String) : void
+ setZip(int) : void
+ getStreetNumber() : int
+ getStreetName() : String
+ getCity() : String
+ getState() : String
+ getZip() : int
*/

package FinalProject;

public class Address {
    private int streetNumber;
    private String streetName;
    private String city;
    private String state;
    private int zip;
    
    Address(){}
    
    Address(String name, String city, int num, int zip, String state) {
        setStreetName(name);
        setCity(city);
        setStreetNumber(num);
        setZip(zip);
        setState(state);
    }  
    
    Address(Address a) {
        streetName = new String(a.getStreetName());
        city = new String(a.getCity());
        streetNumber = a.getNumber();
        zip = a.getZip(); 
        state = new String(a.getState());
    }
    
    public String toString() {
        String temp;
        temp = streetNumber + " " + streetName 
             + "\n" + zip + " " + state + " " + city;
        return temp;
    }
    
     public void setStreetName(String setStreetName) {
        streetName = setStreetName;
    }
    
    public void setCity(String setCity) {
        city = setCity;
    }
    
    public void setStreetNumber(int setStreetNumber) {
        if (setStreetNumber > 0)
            streetNumber = setStreetNumber;
    }
        
    public void setZip(int setZip) {
        if (setZip >= 10000 && setZip <= 99999)
            zip = setZip;
    }
    
    public void setState(String setState) {
        state = setState;
    }
    
    public String getStreetName() {
        return streetName;
    }
    
    public String getCity() {
        return city;
    }
    
    public int getNumber() {
        return streetNumber;
    }
    
    public int getZip() {
        return zip;
    }
    
    public String getState() {
        return state;
    }
    
}

