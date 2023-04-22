package currencyconversion.API.currencyconversion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                //lomboK dependency
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversion {


    private String time_last_update_utc;
    private String time_next_update_utc;
    private String base_code;
    private String target_code;
    private double conversion_rate;
    private double conversion_result;

}
