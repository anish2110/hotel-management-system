import java.util.*;
import java.io.*;
import static java.lang.System.out;

class Entry{
    public static void main(String [] ak){
        Scanner scan = new Scanner(System.in);
        boolean flagthree = true;
        
        while(flagthree){
            try{
                out.print("\nEnter City: ");
                String cityname = scan.next();

                    String pwd = System.getProperty("user.dir");
                    FileWriter file = new FileWriter(pwd+"/City/"+cityname+".txt");

                    out.print("\nNumber of Hotels to be added: ");
                    int nofhotel = scan.nextInt();
                    scan.nextLine();

                    for(int i=0; i<nofhotel; i++){
                        out.print("\nEnter Hotel name: ");
                        String hotelname = scan.nextLine();
                        //scan.nextLine();

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

                            file.write(roomtype+" "+nofrooms+" "+roomprice+"\t");
                        }
                        file.write("\n");
                        scan.nextLine();
                    }
                    file.close();
                    out.println("\n\nAdded Sucessfully!!");

                    //noofavailablecities++;

                    //Admin.menuList();


            }catch(Exception e){
                out.println("\nWrong Input. Please Try Again!!");
                //addCity();
            }
        }
    }
}