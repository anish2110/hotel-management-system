import java.util.*;
import java.io.*;
import java.time.*;
import static java.lang.System.out; //static import out method of System class 


interface Credentials{
    void login(); //abstract method
    void signUp(); //abstract method
    
    static boolean usernameCheck(String username){ //non abstract method
        String pwd = System.getProperty("user.dir");
        File temp = new File(pwd+"/Credentials/"+username+".txt"); //sets the file path
        if(temp.exists()) return false; //check if file exists
        else return true;
    }
    
    static void writeDetails(String name, String username, String password, String dob, String number, String email)throws Exception{
        String pwd = System.getProperty("user.dir");
        FileWriter file = new FileWriter(pwd+"/Credentials/"+username+".txt"); //open file
        
        //write to file
        file.write(password+"\n");
        file.write(name+"\n");
        file.write(dob+"\n");
        file.write(number+"\n");
        file.write(email+"\n");
        
        file.close();
        
        out.println("\nSignUp Successfull!!");
    }
}


interface Admin{
    void login(); //abstract method
    
    static void menuList(){
        out.println("\n1. Add City");
        out.println("2. Add Hotel");
        out.println("3. Check Booking History");
        out.println("4. Exit");
        
        out.print("\n\nSelect option: ");
    }
    
    static boolean cityCheck(String cityname){
        String pwd = System.getProperty("user.dir");
        File temp = new File(pwd+"/City/"+cityname+".txt"); //sets the file path
        
        if(temp.exists()) return false; //check if file exists
        else return true;
    }
}


interface Hotels{
    static Scanner cityFinder(String city)throws Exception{ //non abstract method
        String pwd = System.getProperty("user.dir");
        File file = new File(pwd+"/City/"+city+".txt");
        
        Scanner scanner = new Scanner(file);
        return scanner;
    }
}



class Register implements Credentials{
    static Scanner scan = new Scanner(System.in); //object of Scanner class
    public static Register register = new Register(); //object of Register class
    static HotelManagementSystem hms = new HotelManagementSystem(); //object of HotelManagementSystem class
    static String username;
    static boolean flag;
    
    public void login(){ //implementation of abstract method
        try{
            out.print("\n\nEnter Username:\t");
            username = scan.next();

            out.print("Enter Password:\t");
            String password = scan.next();
            
            String pwd = System.getProperty("user.dir");
            File file = new File(pwd+"/Credentials/"+username+".txt"); //opens file
            Scanner scanner = new Scanner(file);

            String scanned = scanner.nextLine(); //reads file content

            if(scanned.equals(password)){
                out.println("\nLogin Successfull!!");
                flag = true;
            }

            else{
                out.println("\nWrong Password. Try Again!!");
                register.login();
            }
            
        }catch(Exception e){ //catches Exception
            out.println("\nWrong Username. Try Again!!");
            register.login();
        }
    }
    
    public void signUp(){ //implementation of abstract method
        try{
            scan.nextLine();
            out.print("\n\nEnter Name: ");
            String name = scan.nextLine();
            
            out.print("Enter Username: ");
            username = scan.next();
            boolean flagtwo = Credentials.usernameCheck(username); //calls method of interface Credentials
            
            if(flagtwo){
                out.print("Enter Password:\t");
                String password = scan.next();
                
                out.print("Enter DOB(YYYY-MM-DD):\t");
                String date = scan.next();
                LocalDate dob = LocalDate.parse(date);
                
                out.print("Enter Mobile Number:\t");
                String number = scan.next();
                
                out.print("Enter Email:\t");
                String email = scan.next();
                
                Credentials.writeDetails(name,username,password,date,number,email); //calls method of interface Credentials
                
                flag = true;
            }
            else throw new Exception(); //throws Exception
            
        }catch(DateTimeException e){
            out.println("\nWrong Format for DOB. Please Try Again!\n");
            register.signUp();
            
        }catch(Exception d){
            out.println("\nUsername already exists. Please use a different username!\n");
            register.signUp();
        }
    }
}


class AdminPowers extends Register implements Admin{
    public static AdminPowers ap = new AdminPowers(); //object of AdminPowers class
    static int noofavailablecities = 55;
    
    public void login(){ //implementation of abstract method
        try{
            out.print("\n\nEnter Username:\t");
            String username = scan.next();
            
            if(username.equals("admin")){
                out.print("Enter Password:\t");
                String password = scan.next();
                
                if(password.equals("admin123")){
                    out.println("\nLogin Succesfull!!");
                    Admin.menuList(); //calls method of interface Admin
                    powerSelection();
                }
                
                else{
                    out.println("\nWrong password. Try Again!!");
                    ap.login();
                }
            }

            else{
                out.println("\nWrong username. Try Again!!");
                ap.login();
            }
            
        }catch(Exception e){
            out.println("\nSome error occurred. Please Try Again!!");
            ap.login();
        }
    }
    
    static void powerSelection(){
        try{
            int choice = scan.nextInt();
            
            //switch block
            switch(choice){ 
                case 1:
                    addCity();
                    break;
                    
                case 2:
                    addHotel();
                    break;
                    
                case 3:
                    bookingHistory();
                    break;
                    
                case 4:
                    hms.menuList();
                    
                default:
                    throw new Exception(); //throws Exception
            }
            
        }catch(InputMismatchException e){
            scan.nextLine();
            out.println("\nWrong Input. Please Try Again!!");
            powerSelection();
            
        }catch(Exception e){
            out.println("\nWrong Input. Please Try Again!!");
            powerSelection();
        }
    }
    
    static void addCity(){
        try{
            out.print("\nEnter City: ");
            String cityname = scan.next();

            boolean flagthree = Admin.cityCheck(cityname);

            if(flagthree){
                out.print("\nNumber of Hotels to be added: ");
                int nofhotel = scan.nextInt();
                scan.nextLine();
                
                String pwd = System.getProperty("user.dir");
                FileWriter file = new FileWriter(pwd+"/City/"+cityname+".txt"); //opens file
                
                for(int i=0; i<nofhotel; i++){
                    out.print("\nEnter Hotel name: ");
                    String hotelname = scan.nextLine();
                    
                    file.write(hotelname+"\t");
                    
                    out.print("Number of Room Types: ");
                    int nofroomtype = scan.nextInt();
                    
                    for(int j=0; j<nofroomtype; j++){
                        scan.nextLine();
                        out.print("\nEnter Room Type: ");
                        String roomtype = scan.nextLine();
                        
                        out.print("Enter Number of Rooms: ");
                        String nofrooms = scan.next();
                        
                        out.print("Enter Price per night: ");
                        String roomprice = scan.next();
                        
                        file.write(roomtype+" "+nofrooms+" "+roomprice+"\t"); //writes to file
                    }
                    
                    file.write("\n");
                    scan.nextLine();
                }
                
                file.close(); //close file
                out.println("\n\nAdded Sucessfully!!");
                
                noofavailablecities++;
                
                Admin.menuList();
                powerSelection();
            }

            else{
                out.print("\nCity Already Exists!!\n\nWould you like to add Hotels?(Yes/No)");
                String yesno = scan.next();

                if(yesno.equalsIgnoreCase("yes")) addHotel();
                
                else if(yesno.equalsIgnoreCase("no")){
                    Admin.menuList();
                    powerSelection();
                } 
                
                else throw new Exception(); //throws Exception
            }
            
        }catch(InputMismatchException d){
            scan.nextLine();
            out.println("\nWrong Input. Please Try Again!!");
            addCity();
            
        }catch(Exception e){
            out.println("\nWrong Input. Please Try Again!!");
            addCity();
        }
    }
    
    static void addHotel(){
        try{
            out.print("\nEnter City: ");
            String city = scan.next();
            
            String pwd = System.getProperty("user.dir");
            FileWriter file = new FileWriter(pwd+"/City/"+city+".txt",true); //opens File with append mode
            
            out.print("\nNumber of Hotels to be added: ");
                int nofhotel = scan.nextInt();
                scan.nextLine();
                
                for(int i=0; i<nofhotel; i++){
                    out.print("\nEnter Hotel name: ");
                    String hotelname = scan.nextLine();
                    
                    file.append(hotelname+"\t"); //appends to the file
                    
                    out.print("Number of Room Types: ");
                    int nofroomtype = scan.nextInt();
                    
                    for(int j=0; j<nofroomtype; j++){
                        scan.nextLine();
                        out.print("\nEnter Room Type: ");
                        String roomtype = scan.nextLine();
                        
                        out.print("Enter Number of Rooms: ");
                        String nofrooms = scan.next();
                        
                        out.print("Enter Price per night: ");
                        String roomprice = scan.next();
                        
                        file.write(roomtype+" "+nofrooms+" "+roomprice+"\t"); //writes to file
                        
                    }
                    file.write("\n");
                    scan.nextLine();
                    
                }
                file.close(); //close file
                out.println("\n\nAdded Sucessfully!!");
            
            Admin.menuList();
            powerSelection();
            
        }catch(Exception e){
            out.println("\nError Occurred!! Please Try Again.");
            addHotel();
        }
    }
    
    static void bookingHistory(){
        try{
            out.print("\nEnter username of the customer: ");
            String username = scan.next();
            
            String pwd = System.getProperty("user.dir"); //gets current working directory
            
            File file = new File(pwd+"/Bookings/"+username+".txt"); //sets the file path
            Scanner scanner = new Scanner(file); //scanner object of file
            
            File filecred = new File(pwd+"/Credentials/"+username+".txt");
            Scanner sc = new Scanner(filecred);
            
            String password = sc.nextLine();
            String name = sc.nextLine();
            String dob = sc.nextLine();
            String number = sc.nextLine();
            String email = sc.nextLine();
            
            out.println("\nCredentials:\n");
            
            out.println("Name: "+name);
            out.println("Password: "+password);
            out.println("Date of Birth: "+dob);
            out.println("Phone Number: "+number);
            out.println("Email: "+email);
            
            out.println("\n\nBookings:");
            
            while(scanner.hasNext()){
                //reads content of file
                String city = scanner.nextLine();
                String hotel = scanner.nextLine();
                String room = scanner.nextLine();
                String quantity = scanner.nextLine();
                String cin = scanner.nextLine();
                String cout = scanner.nextLine();
                String bill = scanner.nextLine();
                
                //prints the read content
                out.println("\n"+city);
                out.println(hotel);
                out.println(room);
                out.println(quantity);
                out.println(cin);
                out.println(cout);
                out.println(bill);
                out.println("\n");
            }
            
            Admin.menuList();
            powerSelection();
            
        }catch(Exception e){
            out.println(e);
            out.println("\nNo Customer associated with that username. Please Try Again!");
            bookingHistory();
        }
    }
}


class HotelBooking extends Register implements Hotels{
    static Scanner scanner;
    static String cityname, choosenhotel, hotelname, roomname, cin, cout;
    private static int hotelchoice, days, months, years, quantity, bill;
    static AdminPowers ap = new AdminPowers(); //object of AdminPowers class
    
    static void booking(){
        try{
            out.print("\n\n\nWe have our partner Hotels in "+ap.noofavailablecities+"  different cities!!!\n\nEnter City name: ");
            cityname = scan.next();
            out.print("\n");

            scanner = Hotels.cityFinder(cityname); //calls method of Hotels interface
            
            int i=1;
            while(scanner.hasNext()){
                String first = scanner.nextLine();

                String []splits = first.split("\t");
                out.println(i+". "+splits[0]);
                i++;
            }
            scanner.close();
            
            out.print("\nSelect Hotel: ");
            hotelchoice = scan.nextInt();
            
            if(hotelchoice>(i-1)) throw new Exception(); //throws exception
            
            booking(hotelchoice);
            
        }catch(InputMismatchException d){
            scan.nextLine();
            out.println("\nWrong Input. Please Try Again!!!");
            booking();
            
        }catch(FileNotFoundException f){
            out.print("\nSorry!! We currently don't have Hotel partners in this city.\n\nWould you like to check hotels for another City(Y/N): ");
            String yesno = scan.next();
            
            if(yesno.equalsIgnoreCase("y")) booking();
            else hms.menuList();
            
        }catch(Exception e){
            out.println(e);
            out.println("\nWrong Input. Please Try Again!!!"); 
            booking();
        }     
    }
    
    static void booking(int hotelchoice){
        //try blocck
        try{
            Scanner scanner = Hotels.cityFinder(cityname); //calls method of Hotels interface

            int j=0;
            while(hotelchoice>j){
                choosenhotel = scanner.nextLine();
                j++;
            }

            String []hotelrooms = choosenhotel.split("\t"); //splits the string wherever "\t" occurs
            
            out.println("\n");
            
            hotelname = hotelrooms[0];
            
            for(int i=1; i<hotelrooms.length; i++){
                String hoteldetails[] = hotelrooms[i].split(" "); //splits the String wherever " " occurs
                out.println(i+". "+hoteldetails[0]+" "+hoteldetails[1]+"\t "+hoteldetails[2]+" Rooms\tRs. "+hoteldetails[3]+"/night"); //prints the details of hotels
            }
            
            out.print("\nSelect Room: ");
            int roomtype = scan.nextInt();
            
            String hoteldetails[] = hotelrooms[roomtype].split(" "); //splits the string wherever " " occurs
            roomname = hoteldetails[0]+" "+hoteldetails[1];
            
            if(roomtype>(hotelrooms.length))throw new Exception(); //throws Exception
            
            out.print("Enter quantity of Rooms: ");
            quantity = scan.nextInt();
            
            if(quantity>(Integer.parseInt(hoteldetails[2])))throw new Exception(); //throws Exception
            
            out.print("Enter the Check In date(YYYY-MM-DD): ");
            cin = scan.next();
            LocalDate checkin = LocalDate.parse(cin);
            
            LocalDate current = LocalDate.now();
            if(checkin.isBefore(current)) throw new DateTimeException(" ");
            
            out.print("Enter Check Out date(YYYY-MM-DD): ");
            cout = scan.next();
            LocalDate checkout = LocalDate.parse(cout);
            
            Period period = Period.between(checkin,checkout);//calculates time period in between the two dates
            
            days = period.getDays(); //gets the number of days
            months = period.getMonths(); //gets the number of months
            years = period.getYears(); //gets the number of years
            
            if(months == 0 && days<1) throw new DateTimeException(" "); //throws exception
            
            billing(hoteldetails);
            
        }catch(InputMismatchException d){
            scan.nextLine();
            out.println("\nWrong Input. Please Try Again!!!");
            booking(hotelchoice);
            
        }catch(DateTimeException e){
            out.println("\nWrong Date Input. Please Try Again!!!");
            booking(hotelchoice);
            
        }catch(Exception e){
            out.println("\nWrong Input. Please Try Again!!!");
            booking(hotelchoice);
        }        
    }
    
    static void billing(String []hoteldetails){
        //try block
        try{
            if(years == 0 && months == 0) bill = days*quantity*(Integer.parseInt(hoteldetails[3]));
            
            else if(years == 0 && days == 0){
                bill = months*30*quantity*(Integer.parseInt(hoteldetails[3]));
                days = months*30;
            } 
            
            else if(months == 0 && days == 0){
                bill = years*365*quantity*(Integer.parseInt(hoteldetails[3]));
                days = years*365;
            } 
            
            else{
                bill = (days+(months *30)+(years*365))*quantity*(Integer.parseInt(hoteldetails[3]));
                days = days + (months*30) + (years*365);
            } 
            
            out.println("\n\nYour bill for "+days+" nights for "+quantity+" rooms is Rs."+bill+"/-");

            out.print("\nConfirm Booking(Yes/No): ");
            String yesno = scan.next();

            if(yesno.equalsIgnoreCase("yes")){
                if(flag) saveBooking();
                else menuList();
            }

            else if(yesno.equalsIgnoreCase("no")){
                out.print("\nWould you like to search for another City or exit: ");
                String lastchoice = scan.next();
                
                if(lastchoice.equalsIgnoreCase("hotel")) booking(hotelchoice);
                else if(lastchoice.equalsIgnoreCase("city")) booking();
                else if(lastchoice.equalsIgnoreCase("exit")) hms.menuList();
                else throw new Exception(); //throws Exception
            }

            else throw new Exception(); //throws exception
            
        }catch(Exception e){
            out.println("\nWrong Input. Please Try Again!!!");
            billing(hoteldetails);
        }       
    }
    
    static void saveBooking(){
        //try block
        try{
            String pwd = System.getProperty("user.dir");
            FileWriter file = new FileWriter(pwd+"/Bookings/"+username+".txt",true); //opens File with append mode
            
            //writes to file
            file.append("City: "+cityname+"\n");
            file.write("Hotel: "+hotelname+"\n");
            file.write("Room: "+roomname+"\n");
            file.write("Quantity of rooms: "+quantity+"\n");
            file.write("Check In date: "+cin+"\n");
            file.write("Check Out date: "+cout+"\n");
            file.write("Bill: Rs."+bill);
            file.write("\n");
            
            file.close(); //close the file
            
            out.println("\nBooking Sucessfull!!");
            hms.menuList();
            
        }catch(Exception e){
            out.println("\nSome Error Occured. Please Try Again!!");
            saveBooking();
        }
    }
    
    static void menuList(){
        //try block
        try{
            out.println("\n\n1. Login");
            out.println("2. Sign Up");

            out.print("\nSelect option: ");
            int choice = scan.nextInt();

            if(choice == 1){
                register.login();
                saveBooking();
            }  
            
            else if(choice == 2){
                register.signUp();
                saveBooking();
            } 
            
            else throw new Exception(); //throws exception
            
        }catch(InputMismatchException d){
            scan.nextLine();
            out.println("\nWrong Input. Please Try Again!!!");
            menuList();
            
        }catch(Exception e){
            out.println("\nWrong Input. Please Try Again!!!");
            menuList();
        }
    }
}


class HotelManagementSystem extends HotelBooking{
    static AdminPowers ap = new AdminPowers(); //object of AdminPowers class
    
    public static void main(String [] ak){ //main method
        menuList(); 
    }
    
    static void menuList(){
        Register register = new Register(); //object of Register class
        
        try{
            out.println("\n\n\n\n\t\t\t\t\t\tWelcome To Exotic.com\n\n\n\n");
            
            out.println("\n\n1. Login");
            out.println("2. Admin Login");
            out.println("3. SignUp");
            out.println("4. Book Hotel");
            out.println("5. Exit");
            
            out.print("\nChoose option:\t");
            int option = scan.nextInt();
            
            //switch block
            switch(option){
                case 1:
                    register.login(); //calling non static method
                    booking();
                    break;
                    
                case 2:
                    ap.login();
                    break;
                    
                case 3:
                    register.signUp(); //calling non static method
                    booking();
                    break;
                    
                case 4:
                    booking();
                    break;
                    
                case 5:
                    System.exit(0);
                    break;
                    
                default:
                    throw new Exception(); //throws Exception
            }
            
        }catch(InputMismatchException d){
            scan.nextLine();
            out.println("\nWrong Input. Please Try Again!!\n");
            menuList();
            
        }catch(Exception e){
            out.println("\nWrong Input. Please Try Again!!\n");
            menuList();
        }
    }
}