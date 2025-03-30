package task.tasks.task2.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Magazine {
    private String caption;
    private Frequency frequency; //Періодичність виходу журналу
    private LocalDate publicDate;
    private String type;
    private List<Article> topicsInside;
}
