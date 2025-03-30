package consoledInterface.util;

import consoledInterface.ApplicationInit;
import consoledInterface.controller.sub.input.Cin;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.PatternSyntaxException;

import static consoledInterface.controller.sub.output.Cout.cout;

public class ParseUtil {
    public Cin cin;
    public Thread thread;

    public ParseUtil(Cin cin) {
        this.cin = cin;
    }

    public int getParsedInt() {
        AtomicBoolean shouldKeepCycle = new AtomicBoolean(true);
        AtomicInteger atomicInteger = new AtomicInteger(-1);
        CountDownLatch latch = new CountDownLatch(1);
        thread = new Thread(() -> {
            do {
                try {
                    int temp = Integer.parseInt(cin.getLine());

                    ConcurrentUtil.awaitTillCinIsActive(latch, cin);

                    atomicInteger.set(temp);
                    shouldKeepCycle.set(false);
                    latch.countDown();

                } catch (NumberFormatException e) {
                    cout("\nPlease enter a valid digit!\n", ApplicationInit.textColor); // Сообщение об ошибке
                }
            } while (shouldKeepCycle.get());
        });
        thread.start();

        ConcurrentUtil.await(latch);

        return atomicInteger.get();
    }

    public LocalDate getParsedDate() {
        LocalDate tempDate;
        do {
            cin.setCinActive(true);
            var temp = cin.getLine().trim();
            try {
                tempDate = LocalDate.parse(temp);
                break;
            }catch (DateTimeParseException exception)  {
                cout("\nPlease enter date in format YY-MM-DD\n");
            }
        }while (true);
        return tempDate;
    }

    public Period getParsedPeriod(String pattern) {
        String duration;
        String[] yYmMdD;
        Period tempPeriod;

        do {
            cin.setCinActive(true);
            duration = cin.getLine().trim();
            try {
                yYmMdD = duration.split(pattern);
                tempPeriod = Period.parse(
                        MessageFormat.format(
                                "P{0}Y{1}M{2}D",
                                yYmMdD[0],
                                yYmMdD[1],
                                yYmMdD[2])
                );
                if(duration.contains(pattern))
                    break;
                else
                    cout(MessageFormat.format("\nPlease enter date in format YY{0}MM{0}DD\n", pattern));

            }catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                cout(MessageFormat.format("\nPlease enter date in format YY{0}MM{0}DD\n", pattern));
            }

        }while (true);
        return tempPeriod;
    }

    public int getParsedInt(int maxValue, int minValue) {
        AtomicBoolean shouldKeepCycle = new AtomicBoolean(true);
        AtomicInteger atomicInteger = new AtomicInteger(-1);
        CountDownLatch latch = new CountDownLatch(1);
        thread = new Thread(() -> {
            do {
                try {
                    cin.setCinActive(true);
                    int temp = Integer.parseInt(cin.getLine());

                    ConcurrentUtil.awaitTillCinIsActive(latch, cin);

                    if (temp > maxValue || temp < minValue) {
                        cout("\nPlease enter a digit lower than " + maxValue + " and bigger than " + minValue + "!\n", ApplicationInit.textColor);
                    } else {
                        atomicInteger.set(temp);
                        shouldKeepCycle.set(false);
                        latch.countDown();
                    }
                } catch (NumberFormatException e) {

                    cout("\nPlease enter a valid digit!\n", ApplicationInit.textColor); // Сообщение об ошибке
                }
            } while (shouldKeepCycle.get());
        });

        thread.start();

        ConcurrentUtil.await(latch);

        return atomicInteger.get();
    }

    public int getParsedIntByStringValue(String value) {
        AtomicReference<String> parsedValue = new AtomicReference<>(value);
        int temp;

        do {
            cin.setCinActive(true);
            CountDownLatch latch = new CountDownLatch(1);

            parsedValue.set(cin.getLine());

            ConcurrentUtil.awaitTillCinIsActiveInOtherThread(latch, cin);

            try {
                // Ждем завершения ввода
                ConcurrentUtil.await(latch);

                temp = Integer.parseInt(parsedValue.get());

                return temp; // Возвращаем число, если все в порядке
            } catch (NumberFormatException e) {
                cout("\nPlease enter a valid digit!\n", ApplicationInit.textColor); // Сообщение об ошибке
            }

        } while (true);
    }

    public double getParsedDouble() {
        AtomicBoolean shouldKeepCycle = new AtomicBoolean(true);
        AtomicReference<Double> atomicDouble = new AtomicReference<>(-1.0);
        CountDownLatch latch = new CountDownLatch(1);

        thread = new Thread(() -> {
            do {
                try {
                    cin.setCinActive(true);
                    double temp = Double.parseDouble(cin.getLine());
                    atomicDouble.set(temp);
                    shouldKeepCycle.set(false);
                    latch.countDown();

                } catch (NumberFormatException e) {

                    cout("\nPlease enter a valid digit!\n", ApplicationInit.textColor); // Сообщение об ошибке
                }
            } while (shouldKeepCycle.get());
        });

        thread.start();

        ConcurrentUtil.await(latch);

        return atomicDouble.get();
    }

    //Not sure, maybe this: if (temp < Math.nextDown(max) && temp > Math.nextUp(min)) will be better
    public double getParsedDouble(double max, double min) {
        AtomicBoolean shouldKeepCycle = new AtomicBoolean(true);
        AtomicReference<Double> atomicDouble = new AtomicReference<>(-1.0);
        CountDownLatch latch = new CountDownLatch(1);

        thread = new Thread(() -> {
            do {
                try {
                    cin.setCinActive(true);
                    double temp = Double.parseDouble(cin.getLine());

                    if(temp < max && temp > min) {
                        atomicDouble.set(temp);
                        shouldKeepCycle.set(false);
                        latch.countDown();
                    }else
                        cout("\nPlease enter a valid digit!\n", ApplicationInit.textColor);

                } catch (NumberFormatException e) {
                    cout("\nPlease enter a valid digit!\n", ApplicationInit.textColor);
                }
            } while (shouldKeepCycle.get());
        });

        thread.start();

        ConcurrentUtil.await(latch);

        return atomicDouble.get();
    }
}
