package nl.cjib.eventsourcing.api;

import lombok.RequiredArgsConstructor;
import nl.cjib.eventsourcing.api.dto.FinancieleVerplichtingDTO;
import nl.cjib.eventsourcing.core.command.BepaalFinancieleVerplichtingCommand;
import nl.cjib.eventsourcing.core.usecases.BepaalFinancieleVerplichtingUseCase;
import nl.cjib.eventsourcing.mapper.Mapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class FinancieleVerplichtingController {

    private final BepaalFinancieleVerplichtingUseCase bepaalFinancieleVerplichtingUseCase;
    private final Mapper mapper;

    @GetMapping("/financiele-verplichtingen")
    public FinancieleVerplichtingDTO getFinancieleVerplichting(@RequestParam String verplichtingnummer, @RequestParam("peil-datum")   @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate peilDatum) {
        return mapper.map(bepaalFinancieleVerplichtingUseCase.handle(BepaalFinancieleVerplichtingCommand.of(verplichtingnummer, peilDatum)));
    }
}
