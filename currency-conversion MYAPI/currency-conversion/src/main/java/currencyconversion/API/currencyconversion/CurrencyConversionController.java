package currencyconversion.API.currencyconversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@RestController
public class CurrencyConversionController {

    private final String MY_API_KEY = "0afb8998c3ab1a5e8269a4c0";    //properties to save time
    private final String BASE_URL = "https://v6.exchangerate-api.com/v6";



    @GetMapping("/pair/from/{from}/to/{to}/AMOUNT/{AMOUNT}")           //part of the link that matters to change
    public CurrencyConversion conversion(@PathVariable String from,
                                         @PathVariable String to,
                                         @PathVariable double AMOUNT) throws JsonProcessingException, HttpClientErrorException {

        String URL = BASE_URL + "/" + MY_API_KEY + "/pair/" + from + "/" + to + "/" + AMOUNT; // concatenate URL


        RestTemplate restTemplate = new RestTemplate();                     //RestTemplate to test HTTP based restful web services
        String response = restTemplate.getForObject(URL, String.class);     // transforms request into json format


        ObjectMapper objectMapper = new ObjectMapper();  //transforms json in an pojo
        String timeLastUpdate = objectMapper.readTree(response).get("time_last_update_utc").asText();
        String timeNextUpdate = objectMapper.readTree(response).get("time_next_update_utc").asText();
        double conversionRate = objectMapper.readTree(response).get("conversion_rate").asDouble();
        double conversionResult = objectMapper.readTree(response).get("conversion_result").asDouble();


        return new CurrencyConversion(timeLastUpdate, timeNextUpdate, from, to, conversionRate, conversionResult);
    }
}
