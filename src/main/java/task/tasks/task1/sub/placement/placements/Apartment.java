package task.tasks.task1.sub.placement.placements;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task.tasks.task1.sub.placement.Placement;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
public class Apartment extends Placement {
    private int floor;
    private boolean hasElevator;

    public Apartment(String address,
                     int roomsCount,
                     double pricePerMonth,
                     boolean isAlreadyRented,
                     LocalDate dateRentStart,
                     Period dateRentDuration,
                     int floor,
                     boolean hasElevator) {

        super(address,
                roomsCount,
                pricePerMonth,
                isAlreadyRented,
                dateRentStart,
                dateRentDuration
        );

        this.floor = floor;
        this.hasElevator = hasElevator;
    }
    public Apartment(Apartment apartment) {
        super(apartment.address,
                apartment.roomsCount,
                apartment.pricePerMonth,
                apartment.isAlreadyRented,
                apartment.dateRentStart,
                apartment.dateRentDuration
                );
        floor = apartment.floor;
        hasElevator = apartment.hasElevator;
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                        ------------------Apartment-------------------
                        rooms: {0}
                        price per month: {1}
                        address: {2}
                        date when it is possible to start renting: {3}
                        rent duration: {4}
                        is already rented: {5}
                        floor: {6}
                        has elevator: {7}
                        -----------------------------------------------
                        """,
                roomsCount,
                pricePerMonth,
                address,
                dateRentStart,
                dateRentDuration,
                isAlreadyRented,
                floor,
                hasElevator
        );
    }
}
