package nl.cjib.eventsourcing;

import nl.cjib.eventsourcing.api.dto.FinancieleVerplichtingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinancieleVerplichtingIntegrationTest {


    @LocalServerPort
    private String port;
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        testRestTemplate = new TestRestTemplate();
    }

    @Test
    void getFinancieleVerplichting() {

        var response = testRestTemplate
                .getForEntity(getUrl(port, "VERPLICHTINGSNUMMER1", "2023-09-11"), FinancieleVerplichtingDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));

        var dto = response.getBody();
        assertThat(dto).isNotNull();
        assertThat(dto.getOpenstaandSaldo()).isNotNull().isEqualTo(BigDecimal.valueOf(16500.00).setScale(2, RoundingMode.HALF_UP));
        assertThat(dto.getOmschrijving()).isNotNull().isEqualTo("Verkeersboete");
        assertThat(dto.getBetalingsverplichtingen()).hasSize(1);
        assertThat(dto.getGebeurtenissen()).hasSize(6);
    }

    @Test
    void getFinancieleVerplichting_notFound() {

        var response = testRestTemplate.getForEntity(getUrl(port, "VERPLICHTINGSNUMMER2", "2023-09-15"), FinancieleVerplichtingDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(404));
    }

    private static String getUrl(String port, String verplichtingsnummer, String peildatum) {
        return String.format("http://localhost:%s/financiele-verplichtingen?verplichtingnummer=%s&peil-datum=%s", port, verplichtingsnummer, peildatum);
    }
}