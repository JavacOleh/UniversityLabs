package task.tasks.task3.sub.child;

import lombok.*;
import task.tasks.task3.sub.Animal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Kangaroo extends Animal {
    private double jumpHeight;

    @Override
    public String toVoice() {
        return "Rhhhhh";
    }

    @Override
    public double minKgFoodPerDay() {
        return 1.25;
    }
}
