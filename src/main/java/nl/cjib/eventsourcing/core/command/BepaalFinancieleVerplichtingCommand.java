package nl.cjib.eventsourcing.core.command;

import java.time.LocalDate;
import java.util.Objects;

public record BepaalFinancieleVerplichtingCommand(String verplichtingnummer, LocalDate peildatum) {

    public BepaalFinancieleVerplichtingCommand {
        Objects.requireNonNull(verplichtingnummer);
        Objects.requireNonNull(peildatum);
    }

    public static BepaalFinancieleVerplichtingCommand of(String verplichtingnummer, LocalDate peildatum) {
        return new BepaalFinancieleVerplichtingCommand(verplichtingnummer, peildatum);
    }
}
