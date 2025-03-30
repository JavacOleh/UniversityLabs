package task.tasks.task1.sub;

import lombok.Getter;
import lombok.NoArgsConstructor;
import task.tasks.task1.sub.placement.Placement;

import java.util.ArrayList;
import java.util.List;

import static consoledInterface.controller.sub.output.Cout.cout;

@Getter
@NoArgsConstructor
public class PlacementPool {
    private List<Placement> placements;
    {
        placements = new ArrayList<>();
    }

    public PlacementPool(List<Placement> placements) {
        this.placements = placements;
    }

    public void addPlacement(Placement placement) {
        if(placement != null)
            placements.add(placement);
    }

    public void print() {
        cout("\nPlacements:\n");
        if (!placements.isEmpty())
            placements.forEach(s -> cout(s.toString()));
        cout("\n");
    }
}
