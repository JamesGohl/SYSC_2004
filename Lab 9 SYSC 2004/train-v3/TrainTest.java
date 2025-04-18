import java.util.ArrayList;

/**
 * The test class TrainTest.
 *
 * @author Lynn Marshall
 * @author James Gohl
 * @version March 6 2025
 */
public class TrainTest extends junit.framework.TestCase
{
    Train aTrain;
    Car car1, car2, car3, car4;

    /**
     * Default constructor for test class TrainTest
     */
    public TrainTest()
    {
        aTrain = new Train();

        car1 = new Car(1250, true);
        aTrain.addCar(car1);

        car2 = new Car(1300, false);
        aTrain.addCar(car2);

        car3 = new Car(1740, false);
        aTrain.addCar(car3);

        car4 = new Car(1253, true);
        aTrain.addCar(car4);
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

    /**
     * Test empty train
     */
    public void testCreateEmptyTrain()
    {
        Train emptyTrain = new Train();

        /* Verify that a new train has no cars. */
        assertEquals(0, emptyTrain.cars().size());
    }

    /**
     * test add car method
     */
    public void testAddCar()
    {

        ArrayList<Car> cars = aTrain.cars();
        assertEquals(4, cars.size());


        /* Verify that each car added to the train was placed at
         * the end of the list.
         */


        /* Important - assertSame() does not compare the Car objects
         * referred to by car1 and aCar to detemine if they are equal
         * (have the same state). It verifies that car1 an aCar refer to
         * the same object; i.e., that the Car (reference) retrieved by get(0)
         * is the first first that was added to the ArrayList.
         */
        assertSame(car1, cars.get(0));

        assertSame(car2, cars.get(1));

        assertSame(car3, cars.get(2));

        assertSame(car4, cars.get(3));
    }

    /**
     * test issue ticket method
     */
    public void testIssueTicket()
    {

        /* Book all the seats in the 2 business-class car. */
        for (int i = 0; i < Car.BUSINESS_SEATS * 2; i++)
        {
            assertTrue(aTrain.issueTicket(true));
        }

        /* Attempt to book one more business-class seat, even
         * though they are all booked.
         */
        assertFalse(aTrain.issueTicket(true));

        ArrayList<Car> cars = aTrain.cars();

        for (int i = 0; i < Car.BUSINESS_SEATS; i++)
        {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertFalse(cars.get(1).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.BUSINESS_SEATS; i++)
        {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }

        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertTrue(aTrain.issueTicket(false));
        }

        cars = aTrain.cars();

        for (int i = 0; i < Car.BUSINESS_SEATS; i++)
        {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.BUSINESS_SEATS; i++)
        {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }

        /* Book all the seats in the second economy-class car. */
        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertTrue(aTrain.issueTicket(false));
        }

        /* check that all seats are now booked */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++)
        {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }

        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertTrue(cars.get(2).seats()[i].isBooked());
        }

        /* Try to book another business class seat (fails)*/
        assertFalse(aTrain.issueTicket(true));
        /* Try to book another economy class seat (fails)*/
        assertFalse(aTrain.issueTicket(false));
    }

    /**
     * test cancel ticket method
     */
    public void testCancelTicket()
    {

        /* Book all the seats in the business-class car. */
        for (int i = 0; i < Car.BUSINESS_SEATS * 1.5; i++)
        {
            assertTrue(aTrain.issueTicket(true));
        }

        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertTrue(aTrain.issueTicket(false));
        }

        ArrayList<Car> cars = aTrain.cars();

        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked());

        assertTrue(aTrain.cancelTicket(1253, 7));
        assertFalse(cars.get(3).seats()[6].isBooked());

        /* Cancel ticket in a non-existent car. */
        assertFalse(aTrain.cancelTicket(1500, 7));

        /* Cancel ticket in a non-existent seat. */
        assertFalse(aTrain.cancelTicket(1300, 54));

        /* Cancel ticket for a seat that hasn't been booked. */
        assertFalse(aTrain.cancelTicket(1740, 21));
        assertFalse(cars.get(2).seats()[20].isBooked());

        /* Attempt to cancel the same ticket twice. */
        assertTrue(aTrain.cancelTicket(1250, 11));
        assertFalse(cars.get(0).seats()[10].isBooked());

        assertFalse(aTrain.cancelTicket(1250, 11));
        assertFalse(cars.get(0).seats()[10].isBooked());

        /* attempt to cancel un booked seat*/
        assertFalse(aTrain.cancelTicket(1741, 25));
        assertFalse(cars.get(3).seats()[24].isBooked());
    }

    /**
     * Test if you can book a seat after it has been canceled
     */
    public void testBookCancelTicket()
    {


        /* Book all the seats in the business-class car. */
        for (int i = 0; i < Car.BUSINESS_SEATS * 1.5; i++)
        {
            assertTrue(aTrain.issueTicket(true));
        }

        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i < Car.ECONOMY_SEATS; i++)
        {
            assertTrue(aTrain.issueTicket(false));
        }

        //test canceled economy and rebook
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertTrue(aTrain.cancelTicket(1300, 14));
        assertTrue(aTrain.cancelTicket(1300, 27));

        assertTrue(aTrain.issueTicket(false));
        assertTrue(car2.seats()[3].isBooked());

        assertTrue(aTrain.issueTicket(false));
        assertTrue(car2.seats()[13].isBooked());

        assertTrue(aTrain.issueTicket(false));
        assertTrue(car2.seats()[26].isBooked());

        assertTrue(aTrain.issueTicket(false));
        assertTrue(car3.seats()[0].isBooked());

        //test cancel business and rebook
        assertTrue(aTrain.cancelTicket(1250, 4));
        assertTrue(aTrain.cancelTicket(1253, 3));
        assertTrue(aTrain.cancelTicket(1253, 7));

        assertTrue(aTrain.issueTicket(true));
        assertTrue(car1.seats()[3].isBooked());

        assertTrue(aTrain.issueTicket(true));
        assertTrue(car2.seats()[13].isBooked());

        assertTrue(aTrain.issueTicket(true));
        assertTrue(car2.seats()[6].isBooked());

        assertTrue(aTrain.issueTicket(true));
        assertTrue(car2.seats()[15].isBooked());
    }
}
