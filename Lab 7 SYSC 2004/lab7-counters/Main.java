public class Main
{
    public static void main (String[] args)
    {
        System.out.println("Rollover counter:");
        RollOverCounter my_roll = new RollOverCounter();
        for(int i = 0; i < my_roll.maximumCount() + 7; i++)
        {
            System.out.println(my_roll.count());
            my_roll.countUp();

        }
        System.out.println("Rollover counter reset:");
        my_roll.reset();
        System.out.println(my_roll.count());

        System.out.println("Limited counter:");
        LimitedCounter my_lim = new LimitedCounter();
        for(int i = 0; i < my_lim.maximumCount() + 7; i++)
        {
            System.out.println(my_lim.count());
            my_lim.countUp();

        }
        System.out.println("Limited reset:");
        my_lim.reset();
        System.out.println(my_lim.count());
    }
}
