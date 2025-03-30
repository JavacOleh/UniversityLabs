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
public class Cello extends MusicaIInstrument {

    public Cello() {
        super("Виолончель");
    }

    @Override
    public void sound() {
        cout("\n*Звук Виолончели...\n");
    }

    @Override
    public void desc() {
        cout("""
                
                Виолонче́ль (возможно через фр. violoncelle[2], от итал. violoncello[2][3][4][1],
                 уменьшит.-ласкат. от violone — «контрабасик[2][3];
                  маленький контрабас[3]», образованного от violone[3][4]) — смычковый музыкальный инструмент
                   с 4-мя струнами,
                   настроенными по квинтам: Cб Gб Dм Aм.
                
                Виолончель по высоте звучания занимает промежуточное положение между более высоким альтом
                 и низким контрабасом.
                 
                """);
    }

    @Override
    public void history() {
        cout("""
                
                Перша віолончель побачила світ на початку 16 століття.
                 Один з найстаріших примірників інструменту зберігся до наших днів.
                 Це віолончель, виготовлена у 1540-х роках видатним італійським скрипковим майстром Андреа Аматі.
                
                """);
    }
}
