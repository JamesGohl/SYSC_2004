/**
 * Train models a train in a passenger train.
 *
 * @author James Gohl
 * @version 1.00 Feb 2, 2025
 */

import java.util.ArrayList;

public class Train
{
    /**
     * The cars in this train.
     */
    private ArrayList<Car> cars;

    /**
     * Constructs an empty train; i.e., one that has no cars.
     */
    public Train()
    {
        cars = new ArrayList<Car>();
    }

    /**
     * Adds the specified car to this train.
     *
     * @param car to be added
     */
    public void addCar(Car car)
    {
        cars.add(car);
    }

    /**
     * Returns this trains's list of cars. This method is intended for
     * testing purposes only, and should not be called by other objects,
     * as it may be removed from the final version of this class.
     *
     * @return A list of the cars in the train
     */
    public ArrayList<Car> cars()
    {
        return cars;
    }

    /**
     * Attempts to issue a ticket for a business class seat or an
     * economy class seat, as specified by the method's argument.
     * It will attempt to book a seat in the first car of the appropriate
     * type, but if a seat is not available it will attempt to book a seat
     * in the second car of the appropriate type, and so on.
     * A request to issue a ticket in a business-class car will never result
     * in a seat being reserved in an economy-class car, and vice-versa.
     * Returns true if successful, false otherwise.
     *
     * @param businessClassSeat true if seat is business class
     * @return true if a seat is successfully booked
     */
    public boolean issueTicket(boolean businessClassSeat)
    {
        if (businessClassSeat)
        {
            for (Car car : cars)
            {
                if (car.isBusinessClass())
                {
                    if (car.bookNextSeat())
                    {
                        return true;
                    }
                }
            }
        } else
        {
            for (Car car : cars)
            {
                if (!car.isBusinessClass())
                {
                    if (car.bookNextSeat())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Cancels the ticket for the specified seat in the specified car.
     * Returns true if successful, false otherwise.
     * @param carId the id of the car
     * @param seatNo number of seat to be cancelled
     * @return true if successfully canceled ticket
     */
    public boolean cancelTicket(int carId, int seatNo)
    {
        for (Car car: cars)
        {
            if (car.id() == carId)
                return car.cancelSeat(seatNo);
        }
        return false;
    }
}
