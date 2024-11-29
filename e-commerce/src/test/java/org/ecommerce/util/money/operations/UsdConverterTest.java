package org.ecommerce.util.money.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UsdConverterTest {

    @Autowired
    private ApiCurrencyConverterService apiCurrencyConverterService;
    @Autowired
    private UsdConverter usdConverter;

    @Disabled("Since the number of API calls is settled as 100 per month," +
            "this test has been disabled to use the 100 calls in specific scenarios")
    @Test
    void convertFromTo() throws JsonProcessingException {
        final String MXN_CODE = "MXN";
        final String EUR_CODE = "EUR";

        double valueOfAnEuroInMxn = apiCurrencyConverterService.fetchCurrenciesMapFromApi().get(MXN_CODE);

        double valueOfAnMxnInEuro = usdConverter.convertAmountFromTo(MXN_CODE, EUR_CODE
                , new BigDecimal(valueOfAnEuroInMxn)).doubleValue();

        assertEquals(valueOfAnMxnInEuro, 1d);
    }
}