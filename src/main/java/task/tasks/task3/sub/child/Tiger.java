package task.tasks.task3.sub.child;

import lombok.*;
import task.tasks.task3.sub.Animal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Tiger extends Animal {
    private boolean isWinterTiger;

    @Override
    public String toVoice() {
        return "RHHHHH!!!";
    }

    @Override
    public double minKgFoodPerDay() {
        return 1.4;
    }
}
