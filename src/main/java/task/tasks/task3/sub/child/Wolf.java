package task.tasks.task3.sub.child;

import lombok.*;
import task.tasks.task3.sub.Animal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Wolf extends Animal {
    private boolean isWinterWolf;

    @Override
    public String toVoice() {
        return "Uyyuuu";
    }

    @Override
    public double minKgFoodPerDay() {
        return 0.700;
    }
}
