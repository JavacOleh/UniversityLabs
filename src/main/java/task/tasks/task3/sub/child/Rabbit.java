package task.tasks.task3.sub.child;

import lombok.*;
import task.tasks.task3.sub.Animal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Rabbit extends Animal {
    private double jumpHeight;

    @Override
    public String toVoice() {
        return "rrrrh";
    }

    @Override
    public double minKgFoodPerDay() {
        return 0.400;
    }
}
