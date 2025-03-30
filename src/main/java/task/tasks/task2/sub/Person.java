package task.tasks.task2.sub;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
