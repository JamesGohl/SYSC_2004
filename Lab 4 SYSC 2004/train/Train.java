public class Train
{
   private int id; //identifier for the train car
   private boolean businessClass; // if business class car
   final private double PRICE_ECONOMY = 5;
   final private int NUM_SEAT_ECON = 100;
   final private double PRICE_BUSINESS = 50;
   final private int NUM_BUSINESS = 25;
   Seat[] seats;
    public Train(int numberOfCars)
    {

    }
   
    public void addCar(Car car)
    {

    }
   
    public boolean issueTicket(boolean requestedClass)
    {
        return false;
    }
   
    public boolean cancelTicket(int id, int seatNo)
    {
        return false;
    }
}
