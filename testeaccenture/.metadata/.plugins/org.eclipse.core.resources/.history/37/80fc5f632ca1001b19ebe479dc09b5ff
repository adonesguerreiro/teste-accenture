package org.testeaccenture;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Test;

public class MainTest extends TestBase {

    @Test
    public void initTest() throws Exception {
        try {
            theSite();
            vehicleData();
            insurantData();
            productData();
            priceOption();
            sendQuote();
            sendEmail();
        }catch (Exception e){
            driver.close();
            throw new Exception("one of the steps to be performed there was an error");
        }
    }

    @When("opening the browser you should load the requested page")
    public void theSite() throws Exception {
        startBrowser(2);
    }

    @Given("fill in the mandatory fields on the Enter Vehicle Data screen")
    public void vehicleData() throws Exception {
        enterVehicleData("Audi", "Three-Wheeler", "100", "23", "08/08/2020", 9,
                3, 0, 5, "1324546", "89987", "950", "123456",
                "200");
    }

    @Given("fill in the mandatory fields on the Enter Insurance Data screen")
    public void insurantData() throws Exception {
        enterInsurantData("Adones", "Guerreiro", "11/30/1995", 0, "Rua perdizes",
                "brazil", "78300000", "Tangara", 5, 1, "www.google.com.br");
    }

    @Given("fill in the mandatory fields on the Enter Product Data screen")
    public void productData() throws Exception {
        enterProductData("06/15/2021", "5.000.000,00", "Malus 11", "No Coverage"
                , 1, 2);
    }

    @Given("that an option must be chosen")
    public void priceOption() throws Exception {
        radioButtonSPO(1);
    }

    @Given("fill in the mandatory fields on the Send Quote screen")
    public void sendQuote() throws Exception {
        sendQuote("teste@gmail.com", "99999999", "testadeiro", "Teste123", "Teste123",
                "olá mundo");
    }

    @Given("after the Send Quote form and click send validate the message")
    public void sendEmail() throws Exception {
        validateMessage();
    }

}
