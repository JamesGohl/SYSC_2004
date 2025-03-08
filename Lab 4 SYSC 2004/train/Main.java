public class Main
{
    public static void main(String[] args )
    {
        Seat mySeat = new Seat(5, 25.00);
        System.out.println(mySeat.number());
        System.out.println(mySeat.price());
        System.out.println(mySeat.isBooked());
        System.out.println(mySeat.book());
        System.out.println(mySeat.isBooked());
        System.out.println(mySeat.book());
        System.out.println(mySeat.cancelBooking());
        System.out.println(mySeat.isBooked());
        System.out.println(mySeat.cancelBooking());
        System.out.println(mySeat.isBooked());
    }
}
