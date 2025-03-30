package task.tasks.task1.sub.placement.placements;

import lombok.NoArgsConstructor;
import task.tasks.task1.sub.placement.Placement;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
public class House extends Placement {
    private int floorsInside;
    private boolean hasAttic;
    private boolean hasBasement;

    public House(String address,
                 int roomsCount,
                 double pricePerMonth,
                 boolean isAlreadyRented,
                 LocalDate dateRentStart,
                 Period dateRentDuration,
                 int floorsInside,
                 boolean hasAttic,
                 boolean hasBasement) {

        super(address,
                roomsCount,
                pricePerMonth,
                isAlreadyRented,
                dateRentStart,
                dateRentDuration
        );

        this.floorsInside = floorsInside;
        this.hasAttic = hasAttic;
        this.hasBasement = hasBasement;
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                        --------------------House----------------------
                        rooms: {0}
                        price per month: {1}
                        address: {2}
                        date when it is possible to start renting: {3}
                        rent duration: {4}
                        is already rented: {5}
                        floors inside: {6}
                        has attic: {7}
                        has basement: {8}
                        -----------------------------------------------
                        """,
                roomsCount,
                pricePerMonth,
                address,
                dateRentStart,
                dateRentDuration,
                isAlreadyRented,
                floorsInside,
                hasAttic,
                hasBasement
        );
    }
}
