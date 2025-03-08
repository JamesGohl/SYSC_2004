/**
 * The test class CarTest.
 *
 * @author Lynn Marshall, SCE
 * @author James Gohl
 * @version 6 March, 2025
 */
public class CarTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class CarTest
     */
    public CarTest()
    {
    }

    /**
     * Sets up the test fixture.
     * <p>
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     * <p>
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    public void testCreateBusinessCar()
    {
        Car aCar = new Car(1385, true);
        Seat[] seats = aCar.seats();

        /* Verify that the car has the right number of seats. */
        assertEquals(Car.BUSINESS_SEATS, seats.length);

        /* Verify that each seat has the correct number and price. */
        for (int i = 0; i < seats.length; i++)
        {
            assertEquals(i + 1, seats[i].number());
            assertEquals(Car.BUSINESS_SEAT_COST, seats[i].price());
        }
    }

    public void testCreateEconomyCar()
    {
        Car aCar = new Car(1400, false);
        Seat[] seats = aCar.seats();

        /* Verify that the car has the right number of seats. */
        assertEquals(Car.ECONOMY_SEATS, seats.length);

        /* Verify that each seat has the correct number and price. */
        for (int i = 0; i < seats.length; i++)
        {
            assertEquals(i + 1, seats[i].number());
            assertEquals(Car.ECONOMY_SEAT_COST, seats[i].price());
        }
    }

    public void testID()
    {
        Car aCar;
        aCar = new Car(1385, true);
        assertEquals(1385, aCar.id());
        aCar = new Car(1400, false);
        assertEquals(1400, aCar.id());
    }

    public void testIsBusinessClass()
    {
        Car aCar;
        aCar = new Car(1385, true);
        assertTrue(aCar.isBusinessClass());
        aCar = new Car(1400, false);
        assertFalse(aCar.isBusinessClass());
    }

    /**
     * test the book next seat in the car class
     */
    public void testBookNextSeat()
    {
        Car aCar;
        aCar = new Car(1234, true);

        Seat[] seats = aCar.seats();

        /* Verify that no seats are booked. */
        for (int i = 0; i < seats.length; i++)
        {
            assertFalse(seats[i].isBooked());
        }

        /* Verify that the seats are booked consecutively,
         * starting with Seat #1.
         */
        for (int i = 0; i < seats.length; i++)
        {
            seats = aCar.seats();
            assertFalse(seats[i].isBooked()); // not booked
            assertTrue(aCar.bookNextSeat()); // book it
            assertTrue(seats[i].isBooked()); // now booked
            if (i != seats.length - 1)
            {
                assertFalse(seats[i + 1].isBooked()); // but next isn't
            }
        }

        /* Try to book a seat now that all the seats have been booked. */
        assertFalse(aCar.bookNextSeat());


        //test economy car
        Car aCar2;
        aCar2 = new Car(1234, false);

        Seat[] seats2 = aCar2.seats();

        /* Verify that no seats are booked. */
        for (Seat seat : seats2)
        {
            assertFalse(seat.isBooked());
        }

        /* Verify that the seats are booked consecutively,
         * starting with Seat #1.
         */
        for (int i = 0; i < seats2.length; i++)
        {
            seats2 = aCar2.seats();
            assertFalse(seats2[i].isBooked()); // not booked
            assertTrue(aCar2.bookNextSeat()); // book it
            assertTrue(seats2[i].isBooked()); // now booked
            if (i != seats2.length - 1)
            {
                assertFalse(seats2[i + 1].isBooked()); // but next isn't
            }
        }

        /* Try to book a seat now that all the seats have been booked. */
        assertFalse(aCar2.bookNextSeat());
    }

    /**
     * test the cancel seat method in car calls
     * does so by looping through an entire car
     * a seeing if it can be canceled, then booking them
     * then canceling it all. It then does it again not in number order,
     * incase th numbering system is off.
     * Tries to cancel non-existent set 50
     * Does this for economy and business
     */
    public void testCancelSeat()
    {
        Car aCar;
        aCar = new Car(1234, true);

        Seat[] seats = aCar.seats();

        /* Cancel seat 0. cancelSeat() should return false, as there
         * is no seat 0.
         */
        assertFalse(aCar.cancelSeat(0));

        /* Try cancelling a seat whose number is one higher than
         * the highest valid seat number (seats.length - 1).
         * cancelSeat() should return false.
         */
        assertFalse(aCar.cancelSeat(seats.length));

        /* Try cancelling all the seats in the car, even though
         * they haven't been booked. cancelSeat() should
         * return false.
         */
        for (int i = 0; i < seats.length; i++)
        {
            assertFalse(aCar.cancelSeat(i + 1));
        }

        /* Book all the seats */
        for (int i = 0; i < seats.length; i++)
        {
            aCar.bookNextSeat();
        }

        /* Try cancelling all the seats in the car.
         */
        for (int i = 0; i < seats.length; i++)
        {
            assertTrue(aCar.cancelSeat(i + 1));
        }

        /* In case seat numbers are off, try some more tests.
         */
        Car bCar;
        bCar = new Car(4321, true);
        // book 2 seats
        assertTrue(bCar.bookNextSeat());
        assertTrue(bCar.bookNextSeat());
        // try to cancel the 3rd (not booked)
        assertFalse(bCar.cancelSeat(3));
        // cancel the 1st and 2nd (were both booked)
        assertTrue(bCar.cancelSeat(1));
        assertTrue(bCar.cancelSeat(2));

        //test cancel seat 50
        assertFalse(bCar.cancelSeat(50));


        // test economy car
        Car aCar2;
        aCar2 = new Car(1234, false);

        Seat[] seats2 = aCar2.seats();

        /* Cancel seat 0. cancelSeat() should return false, as there
         * is no seat 0.
         */
        assertFalse(aCar2.cancelSeat(0));

        /* Try cancelling a seat whose number is one higher than
         * the highest valid seat number (seats.length - 1).
         * cancelSeat() should return false.
         */
        assertFalse(aCar2.cancelSeat(seats.length));

        /* Try cancelling all the seats in the car, even though
         * they haven't been booked. cancelSeat() should
         * return false.
         */
        for (int i = 0; i < seats2.length; i++)
        {
            assertFalse(aCar2.cancelSeat(i + 1));
        }

        /* Book all the seats */
        for (int i = 0; i < seats2.length; i++)
        {
            aCar2.bookNextSeat();
        }

        /* Try cancelling all the seats in the car.
         */
        for (int i = 0; i < seats.length; i++)
        {
            assertTrue(aCar2.cancelSeat(i + 1));
        }

        /* In case seat numbers are off, try some more tests.
         */
        Car bCar2;
        bCar2 = new Car(4321, false);
        // book 2 seats
        assertTrue(bCar2.bookNextSeat());
        assertTrue(bCar2.bookNextSeat());
        // try to cancel the 3rd (not booked)
        assertFalse(bCar2.cancelSeat(3));
        // cancel the 1st and 2nd (were both booked)
        assertTrue(bCar2.cancelSeat(1));
        assertTrue(bCar2.cancelSeat(2));

        //test cancel seat 50
        assertFalse(bCar2.cancelSeat(50));

    }
}
