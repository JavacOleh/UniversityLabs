package task.tasks.task2.sub;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
    private Person author;
    private String caption;
    private double rate;
}
