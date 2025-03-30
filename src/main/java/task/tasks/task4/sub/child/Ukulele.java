package task.tasks.task4.sub.child;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import task.tasks.task4.sub.MusicaIInstrument;

import static consoledInterface.controller.sub.output.Cout.cout;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Ukulele extends MusicaIInstrument {
    public Ukulele() {
        super("Укулеле");
    }

    @Override
    public void sound() {
        cout("\n*Звук Укулеле...\n");
    }

    @Override
    public void desc() {
        cout("""
                
                Укуле́ле — четырёхструнная разновидность гитары, используемая для аккордового сопровождения песен и игры соло[1].
                
                """);
    }

    @Override
    public void history() {
        cout("""
                
                Укулеле появилась на Гавайских островах во второй половине XIX века,
                куда её, под названием машети да браса (порт. machete da braça), 
                завезли португальцы с острова Мадейра[1].
                 Первый магазин по продаже укулеле на Гавайях был открыт
                  в 1880 году Мануэлем Нуньесом (1843−1922[2])[3].
                   Гавайцы используют укулеле для исполнения сентиментальных песен на
                    английском языке и музыки в «гавайском стиле»
                
                """);
    }
}
