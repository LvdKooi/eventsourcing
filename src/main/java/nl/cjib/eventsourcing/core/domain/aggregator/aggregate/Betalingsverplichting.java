package nl.cjib.eventsourcing.core.domain.aggregator.aggregate;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Betalingsverplichting(String betalingskenmerk,
                                    LocalDate vervaldatum,
                                    String omschrijving,
                                    String betaalwijze,
                                    BigDecimal bedrag) {
}
