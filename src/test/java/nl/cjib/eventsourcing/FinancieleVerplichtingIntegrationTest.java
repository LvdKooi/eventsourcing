package nl.cjib.eventsourcing;

import nl.cjib.eventsourcing.api.dto.FinancieleVerplichtingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
class FinancieleVerplichtingIntegrationTest {

    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        testRestTemplate = new TestRestTemplate();
    }

    @Test
    void getFinancieleVerplichting() {

        var response = testRestTemplate.getForEntity("http://localhost:8080/financiele-verplichtingen?verplichtingnummer=VERPLICHTINGNUMMER1&peilDatum=2023-09-15", FinancieleVerplichtingDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));

        var dto = response.getBody();
        assertThat(dto).isNotNull();
        assertThat(dto.getOpenstaandSaldo()).isNotNull().isEqualTo(BigDecimal.valueOf(16500));
        assertThat(dto.getOmschrijving()).isNotNull().isEqualTo("Verkeersboete");
        assertThat(dto.getBetalingsverplichtingen()).hasSize(1);
        assertThat(dto.getGebeurtenissen()).hasSize(6);
    }
}