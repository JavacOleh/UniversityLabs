package task.tasks.task4.sub.child;

import lombok.*;
import task.tasks.task4.sub.MusicaIInstrument;

import static consoledInterface.controller.sub.output.Cout.cout;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Violin extends MusicaIInstrument {

    public Violin() {
        super("Скрипка");
    }

    @Override
    public void sound() {
        cout("\n*Играет скрипка...\n");
    }

    @Override
    public void desc() {
        cout("""
                
                Скри́пка — струнно-смычковый музыкальный инструмент с четырьмя струнами, настроенными по квинтам: Gм D1 A1 E2.
                
                Самая высокая регистровая разновидность скрипичного семейства,
                 ниже которой располагаются альт, виолончель и контрабас.
                  Вместе с фортепиано скрипка является главным инструментом академической (классической) музыки.
                   С середины XVIII века она составляет основу симфонического оркестра и струнного квартета.
                    Как народный инструмент продолжает бытовать среди поляков
                    , белорусов (см. троисти музыки),
                     евреев (см. клезмер),
                      северо-западных русских,
                       чехов, латышей,
                        шведов, норвежцев, 
                        эстонцев, венгров,
                         румын, молдаван и других народов.
                 В США применяется в музыке кантри[1], в Индии — в классической музыке традиции карнатака.
                 
                """);
    }

    @Override
    public void history() {

    }
}
