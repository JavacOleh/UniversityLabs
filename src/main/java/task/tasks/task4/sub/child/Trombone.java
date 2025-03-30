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
public class Trombone extends MusicaIInstrument {

    public Trombone() {
        super("Тромбон");
    }

    @Override
    public void sound() {
        cout("\n*Звук тромбона\n");
    }

    @Override
    public void desc() {
        cout("""
                
                Тромбон (итал. trombone — большая труба[1]) — медный духовой музыкальный инструмент,
                 отличительной особенностью которого является наличие передвижной кулисы,
                  плавно изменяющей объём воздуха в инструменте и соответственно высоту его звучания.
                   Существует несколько регистровых разновидностей тромбона,
                 основными из которых является тенор-тромбон и тенор-бас-тромбон в строе Си-бемоль (in B)
                
                """);
    }

    @Override
    public void history() {
        cout("""
                
                Появление тромбона относится к XV веку.
                 Принято считать, что непосредственными предшественниками этого инструмента были кулисные трубы,
                  при игре на которых у музыканта была возможность передвигать трубку инструмента,
                   таким образом получая хроматический звукоряд.
                    Такие трубы использовались для удвоения голосов церковного хора,
                     учитывая сходство тембра трубы с человеческим голосом.
                 Нужно только было достичь сходства интонации, для чего и сделали кулису, дающую хроматизм и вибрато
                
                """);
    }
}
