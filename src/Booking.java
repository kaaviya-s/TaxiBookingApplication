import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Booking {
    public static List<Taxi> createTaxis(int n){
        List<Taxi> taxis = new ArrayList<Taxi>();
        for(int i = 0 ; i< n ; i++){
            Taxi taxi = new Taxi();
            taxis.add(taxi);
        }
        return taxis;
    }

    public static List<Taxi> getFreeTaxi(List<Taxi> taxis , char pickup_point , int pickup_time){
        List<Taxi> freeTaxi = new ArrayList<>();
        for(Taxi taxi : taxis){
            if(taxi.freetime <= pickup_time && (Math.abs(((taxi.currentspot - '0') - (pickup_point-'0'))) <= (pickup_time - taxi.freetime))){
                freeTaxi.add(taxi);
            }
        }
        return freeTaxi;
    }

    public static void bookTaxi(List<Taxi> freeTaxis , int customer_id , int pickuptime , char pickpoint , char droppoint){
        int min = Integer.MAX_VALUE;
        int earnings = 0;
        char nextStop=0;
        int droptime = 0;
        int nextfreetime = 0;
        String tripDetails="";

        Taxi bookedTaxi = null;

        for(Taxi taxi : freeTaxis){
            //Distance between every other point id 15Kms
            int distBtwPickAndDrop = Math.abs((taxi.currentspot -'0') - (pickpoint - '0'))* 15;
            if(distBtwPickAndDrop < min){
                bookedTaxi = taxi;

                // earnings  = 100 + (distance-5) * 10;
                earnings = (distBtwPickAndDrop -5) * 10 + 100;

                //droptime calculation
                droptime = pickuptime + (distBtwPickAndDrop )/15;

                nextStop = droppoint;

                nextfreetime = droptime;

                tripDetails = customer_id + "                          " + customer_id + "               " + pickpoint + "             "+
                droppoint + "             "+pickuptime+ "                   "+droptime+"                "+earnings;
            }
        }

        bookedTaxi.setDetails(true,nextStop,nextfreetime,bookedTaxi.totalEarnings+earnings , tripDetails);
        System.out.println("Taxi "+ bookedTaxi.taxi_id +" is booked");
    }

    public static void main(String[] args) {
        int userChoice=0;
        int id = 1;
        Scanner sc = new Scanner(System.in);

        List<Taxi> taxis = createTaxis(4);

        while(true){
            System.out.println("Enter your Choice: ");
            System.out.println(" 1. Print Taxis details \n 2. Book Taxi");
            userChoice = sc.nextInt();

            switch (userChoice){
                case 1:
                    for(Taxi t : taxis){
                        t.printTaxiDetails();
                    }
                    for(Taxi t: taxis){
                        t.printDetails();
                    }
                    break;
                case 2:
                    char pickuppoint;
                    char droppoint;
                    int pickuptime;
                    System.out.println("Enter your choice of pickup place: ");
                    pickuppoint = sc.next().charAt(0);
                    System.out.println("Enter your choice of drop place: ");
                    droppoint = sc.next().charAt(0);
                    System.out.println("Enter your choice pickup time: ");
                    pickuptime = sc.nextInt();

                    if(pickuppoint < 'A' || pickuppoint > 'F' || droppoint < 'A' || droppoint > 'F'){
                        System.out.println("Invalid places. Valid pick up and drop places are A, B, C, D, E, F. Exitting!");
                    }

                    List<Taxi> taxi = getFreeTaxi(taxis, pickuppoint , pickuptime);
                    if(taxi.size() == 0){
                        System.out.println("No Taxi Available at time. Choose other options!");
                        continue;
                    }
                    Collections.sort(taxi , (a,b) -> (a.totalEarnings - b.totalEarnings));

                    bookTaxi(taxi , id , pickuptime , pickuppoint , droppoint);
                    id++;
                    break;
                default:
                    return;
            }
        }
    }
}