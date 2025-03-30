package task.tasks.task1.sub.placement;

import consoledInterface.ApplicationInit;
import consoledInterface.controller.sub.input.Cin;
import consoledInterface.util.ParseUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

import static consoledInterface.controller.sub.output.Cout.cout;

@NoArgsConstructor
@Getter
public abstract class Placement {
    protected String address;
    protected int roomsCount;
    protected double pricePerMonth;
    protected boolean isAlreadyRented;
    protected LocalDate dateRentStart;
    protected Period dateRentDuration;

    public Placement(String address,
                     int roomsCount,
                     double pricePerMonth,
                     boolean isAlreadyRented,
                     LocalDate dateRentStart,
                     Period dateRentDuration) {
        this.address = address;
        this.roomsCount = roomsCount;
        this.pricePerMonth = pricePerMonth;
        this.isAlreadyRented = isAlreadyRented;
        this.dateRentStart = dateRentStart;
        this.dateRentDuration = dateRentDuration;
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                        ------------------Placement-------------------
                        rooms: {0}
                        price per month: {1}
                        address: {2}
                        date when it is possible to start renting: {3}
                        rent duration: {4}
                        is already rented: {5}
                        -----------------------------------------------
                        """,
                roomsCount,
                pricePerMonth,
                address,
                dateRentStart,
                dateRentDuration,
                isAlreadyRented
        );
    }
}
