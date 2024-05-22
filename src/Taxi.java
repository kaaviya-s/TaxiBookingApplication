import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.List;

public class Taxi {
    static int taxicount= 0;
    int taxi_id;
    boolean booked;
    char currentspot;
    int freetime;
    int totalEarnings = 0;
    List<String> trips;

    public Taxi(){
        this.booked = false;
        this.taxicount = taxicount +1;
        this.taxi_id = this.taxicount ;
        this.freetime = 6;
        this.currentspot='A';
        this.totalEarnings = 0;
        trips = new ArrayList<String>();
    }

    public void setDetails(boolean booked,char currentspot , int freetime , int totalEarnings , String tripDetail){
        this.booked = booked;
        this.currentspot = currentspot;
        this.totalEarnings = totalEarnings;
        this.freetime =freetime;
        this.trips.add(tripDetail);
    }
    public void printDetails(){
        System.out.println("Taxi- "+this.taxi_id + " Total Earnings -" +this.totalEarnings);
        System.out.println("TaxiID          BookingID           CustomerID          From            To          PickUpTime          DropTime            Amount");
        for(String trip : trips){
            System.out.println(taxi_id +"               "+trip);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printTaxiDetails(){
        System.out.println("Taxi- " + this.taxi_id + " TotalEarnings - "+this.totalEarnings + " CurrentSpot- " + this.currentspot);
    }
}
