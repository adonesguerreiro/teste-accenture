package org.testeaccenture;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestBase {

    WebDriver driver = null;

    public void startBrowser(int browserIndex) throws Exception {
        try {

            if (browserIndex == 0) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

            } else if (browserIndex == 1) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

            } else if (browserIndex == 2) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();

            } else if (browserIndex == 3) {
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
            }
            driver.manage().window().maximize();
            driver.get("http://sampleapp.tricentis.com/101/app.php");

        } catch (Exception e) {
            throw new Exception("Browser does not exists");
        }
    }

    public void enterVehicleData(String make, String model, String ccm, String kw, String dateManufacture, int nSeats,
                                 int nSeats2, int rightHDrive, int fuelType, String payLoad, String kg, String listPrice,
                                 String lPlateNumber, String mi)
            throws Exception {

        try {
            waitElementVisible("#make");
            WebElement makeField = $("#make");
            makeField.click();
            Thread.sleep(1000);
            List<WebElement> makeList = $("#make option", null);
            for (int i = 0; i < makeList.size(); i++) {
                if (makeList.get(i).getText().equalsIgnoreCase(make)) {
                    makeList.get(i).click();
                    break;
                }
            }

            waitElementVisible("#model");
            WebElement modelField = $("#model");
            modelField.click();
            Thread.sleep(1000);
            List<WebElement> modelList = $("#model option", null);
            for (int i = 0; i < modelList.size(); i++) {
                if (modelList.get(i).getText().equalsIgnoreCase(model)) {
                    modelList.get(i).click();
                    break;
                }
            }

            waitElementVisible("#cylindercapacity");
            WebElement cylinderField = $("#cylindercapacity");
            cylinderField.sendKeys(ccm);
            Thread.sleep(2000);
            if (cylinderField.getCssValue("background-color").equals("rgba(254, 236, 235, 1)")) {
                cylinderField.clear();
                Random r = new Random();
                int result = r.nextInt(2000) + 1;
                cylinderField.sendKeys(String.valueOf(result));
            }

            waitElementVisible("#engineperformance");
            WebElement enginerField = $("#engineperformance");
            enginerField.sendKeys(kw);
            Thread.sleep(2000);
            if (enginerField.getCssValue("background-color").equals("rgba(254, 236, 235, 1)")) {
                enginerField.clear();
                Random r = new Random();
                int result = r.nextInt(2000) + 1;
                enginerField.sendKeys(String.valueOf(result));
            }

            waitElementVisible("#dateofmanufacture");
            WebElement dataField = $("#dateofmanufacture");
            dataField.sendKeys(dateManufacture);

            waitElementVisible("#numberofseats");
            Select numberField = new Select($("#numberofseats"));
            numberField.selectByIndex(nSeats);

            radioButtonRHD(rightHDrive);

            waitElementVisible("#numberofseatsmotorcycle");
            Select numberField2 = new Select($("#numberofseatsmotorcycle"));
            numberField2.selectByIndex(nSeats2);

            waitElementVisible("#fuel");
            Select fuelField = new Select($("#fuel"));
            fuelField.selectByIndex(fuelType);

            waitElementVisible("#payload");
            WebElement payField = $("#payload");
            payField.sendKeys(payLoad);
            Thread.sleep(2000);
            if (payField.getCssValue("background-color").equals("rgba(254, 236, 235, 1)")) {
                payField.clear();
                Random r = new Random();
                int result = r.nextInt(1000) + 1;
                payField.sendKeys(String.valueOf(result));
            }

            waitElementVisible("#totalweight");
            WebElement totalField = $("#totalweight");
            totalField.sendKeys(kg);
            Thread.sleep(2000);
            if (totalField.getCssValue("background-color").equals("rgba(254, 236, 235, 1)")) {
                totalField.clear();
                Random r = new Random();
                int result = r.nextInt(49900) + 100;
                totalField.sendKeys(String.valueOf(result));
            }

            waitElementVisible("#listprice");
            WebElement listField = $("#listprice");
            listField.sendKeys(listPrice);
            Thread.sleep(2000);
            if (listField.getCssValue("background-color").equals("rgba(254, 236, 235, 1)")) {
                listField.clear();
                Random r = new Random();
                int result = r.nextInt(99500) + 500;
                listField.sendKeys(String.valueOf(result));
            }

            waitElementVisible("#licenseplatenumber");
            WebElement plateField = $("#licenseplatenumber");
            plateField.sendKeys(lPlateNumber);
            fieldInvalid(plateField);

            waitElementVisible("#annualmileage");
            WebElement annualField = $("#annualmileage");
            annualField.sendKeys(mi);
            Thread.sleep(2000);
            if (annualField.getCssValue("background-color").equals("rgba(254, 236, 235, 1)")) {
                annualField.clear();
                Random r = new Random();
                int result = r.nextInt(99900) + 100;
                annualField.sendKeys(String.valueOf(result));
            }

            waitElementVisible("#nextenterinsurantdata");
            WebElement buttonNext = $("#nextenterinsurantdata");
            buttonNext.click();

        } catch (Exception e) {
            throw new Exception("It was not possible to click on the element");
        }

    }

    public void radioButtonRHD(int opc) throws Exception {
        String option = "#righthanddriveyes";
        switch (opc) {
            case 0: {
                ((JavascriptExecutor) driver).executeScript("$('" + option + "').click()");
                break;
            }
            case 1: {
                ((JavascriptExecutor) driver).executeScript("$('" + option.replace("yes", "no") + "')" +
                        ".click()");
                break;
            }
            default:
                throw new Exception("Radio button option invalid!\n");

        }
    }

    public void enterInsurantData(String name, String lastname, String dateBirth, int gender, String address, String country,
                                  String zipCode, String city, int occupation, int hobbies, String website)
            throws Exception {

        try {

            waitElementVisible("#firstname");
            WebElement nameField = $("#firstname");
            nameField.sendKeys(name);
            fieldInvalid(nameField);

            waitElementVisible("#lastname");
            WebElement lastField = $("#lastname");
            lastField.sendKeys(lastname);
            fieldInvalid(lastField);

            waitElementVisible("#birthdate");
            WebElement dateField = $("#birthdate");
            dateField.sendKeys(dateBirth);
            fieldInvalid(dateField);

            radioButtonGender(gender);

            waitElementVisible("#streetaddress");
            WebElement streetAddress = $("#streetaddress");
            streetAddress.sendKeys(address);

            waitElementVisible("#streetaddress");
            WebElement countryField = $("#country");
            countryField.click();
            Thread.sleep(1000);
            List<WebElement> countryList = $("#country option", null);
            for (int i = 0; i < countryList.size(); i++) {
                if (countryList.get(i).getText().equalsIgnoreCase(country)) {
                    countryList.get(i).click();
                    break;
                }
            }

            waitElementVisible("#zipcode");
            WebElement zipCodeField = $("#zipcode");
            zipCodeField.sendKeys(zipCode);
            fieldInvalid(zipCodeField);

            waitElementVisible("#city");
            WebElement cityField = $("#city");
            cityField.sendKeys(city);
            fieldInvalid(cityField);

            waitElementVisible("#city");
            Select occupationField = new Select($("#occupation"));
            occupationField.selectByIndex(occupation);

            checkBoxHobbies(hobbies);

            waitElementVisible("#website");
            WebElement webSiteField = $("#website");
            webSiteField.sendKeys(website);
            fieldInvalid(webSiteField);

            waitElementVisible("#nextenterproductdata");
            WebElement buttonNext = $("#nextenterproductdata");
            buttonNext.click();

        } catch (Exception e) {
            throw new Exception("It was not possible to click or find element");
        }
    }

    public void radioButtonGender(int opc) throws Exception {
        String option = "#gendermale";
        switch (opc) {
            case 0: {
                ((JavascriptExecutor) driver).executeScript("$('" + option + "').click()");
                break;
            }
            case 1: {
                ((JavascriptExecutor) driver).executeScript("$('" + option.replace("male", "female") + "')" +
                        ".click()");
                break;
            }
            default:
                throw new Exception("Radio button option invalid!\n");

        }
    }

    public void checkBoxHobbies(int opc) throws Exception {
        switch (opc) {
            case 0: {
                ((JavascriptExecutor) driver).executeScript("$('#speeding').click()");
                break;
            }
            case 1: {
                ((JavascriptExecutor) driver).executeScript("$('#bungeejumping').click()");
                break;
            }
            case 2: {
                ((JavascriptExecutor) driver).executeScript("$('#cliffdiving').click()");
                break;
            }
            case 3: {
                ((JavascriptExecutor) driver).executeScript("$('#skydiving').click()");
                break;
            }
            case 4: {
                ((JavascriptExecutor) driver).executeScript("$('#other').click()");
                break;
            }
            default:
                throw new Exception("Checkbox option invalid!\n");

        }
    }

    public void enterProductData(String startDate, String insuranceSum, String meritRating, String damageInsurance,
                                 int optProducts, int courtesyCar) throws Exception {

        try {
            waitElementVisible("#startdate");
            WebElement startDateField = $("#startdate");
            startDateField.sendKeys(startDate);
            fieldInvalid(startDateField);

            waitElementVisible("#insurancesum");
            WebElement countryField = $("#insurancesum");
            countryField.click();
            Thread.sleep(1000);
            List<WebElement> insuranceList = $("#insurancesum option", null);
            for (int i = 0; i < insuranceList.size(); i++) {
                if (insuranceList.get(i).getText().toLowerCase(Locale.ROOT).equals(insuranceSum.toLowerCase())) {
                    insuranceList.get(i).click();
                    break;
                }
            }

            waitElementVisible("#meritrating");
            WebElement merityField = $("#meritrating");
            merityField.click();
            Thread.sleep(1000);
            List<WebElement> merityList = $("#meritrating option", null);
            for (int i = 0; i < merityList.size(); i++) {
                if (merityList.get(i).getText().equalsIgnoreCase(meritRating)) {
                    merityList.get(i).click();
                    break;
                }
            }

            waitElementVisible("#damageinsurance");
            WebElement damageField = $("#damageinsurance");
            damageField.click();
            Thread.sleep(1000);
            List<WebElement> damageList = $("#damageinsurance option", null);
            for (int i = 0; i < damageList.size(); i++) {
                if (damageList.get(i).getText().equalsIgnoreCase(damageInsurance)) {
                    damageList.get(i).click();
                    break;
                }
            }

            checkboxOptProducts(optProducts);

            waitElementVisible("#damageinsurance");
            Select courtesyField = new Select($("#courtesycar"));
            courtesyField.selectByIndex(courtesyCar);

            waitElementVisible("#nextselectpriceoption");
            WebElement buttonNext = $("#nextselectpriceoption");
            buttonNext.click();
        } catch (Exception e) {
            throw new Exception("It was not possible to click or find element");
        }
    }

    public void checkboxOptProducts(int opc) throws Exception {
        switch (opc) {
            case 0: {
                ((JavascriptExecutor) driver).executeScript("$('#EuroProtection').click()");
                break;
            }
            case 1: {
                ((JavascriptExecutor) driver).executeScript("$('#LegalDefenseInsurance').click()");
                break;
            }
            default:
                throw new Exception("Checkbox option invalid!\n");
        }
    }

    public void radioButtonSPO(int opc) throws Exception {
        switch (opc) {
            case 0: {
                ((JavascriptExecutor) driver).executeScript("$('#selectsilver').click()");
                break;
            }
            case 1: {
                ((JavascriptExecutor) driver).executeScript("$('#selectgold').click()");
                break;
            }
            case 2: {
                ((JavascriptExecutor) driver).executeScript("$('#selectplatinum').click()");
                break;
            }
            case 3: {
                ((JavascriptExecutor) driver).executeScript("$('#selectultimate').click()");
                break;
            }
            default:
                throw new Exception("Checkbox option invalid!\n");
        }

        Thread.sleep(3000);
        WebElement buttonNext = $("#nextsendquote");
        buttonNext.click();
    }

    public void sendQuote(String email, String phone, String username, String password, String confirmPass,
                          String comments) throws Exception {
        try {
            waitElementVisible("#email");
            WebElement emailField = $("#email");
            emailField.sendKeys(email);
            fieldInvalid(emailField);

            waitElementVisible("#phone");
            WebElement phoneField = $("#phone");
            phoneField.sendKeys(phone);
            fieldInvalid(phoneField);

            waitElementVisible("#username");
            WebElement userField = $("#username");
            userField.sendKeys(username);
            fieldInvalid(userField);

            waitElementVisible("#password");
            WebElement passwordField = $("#password");
            passwordField.sendKeys(password);
            fieldInvalid(passwordField);

            waitElementVisible("#confirmpassword");
            WebElement confPasswordField = $("#confirmpassword");
            confPasswordField.sendKeys(confirmPass);
            fieldInvalid(confPasswordField);

            waitElementVisible("#Comments");
            WebElement commentsField = $("#Comments");
            commentsField.sendKeys(comments);

            waitElementVisible("#sendemail");
            WebElement sendEmail = $("#sendemail");
            sendEmail.click();
        } catch (Exception e) {
            throw new Exception("It was not possible to click or find element");
        }

    }

    public void validateMessage() throws Exception {

        try {
            waitElementVisible("#LoadingPDF");
            waitElementVisible(".sweet-alert.showSweetAlert.visible");
            WebElement message = $(".sweet-alert.showSweetAlert.visible");

            if (message.isDisplayed() && message.getText().contains("Sending e-mail success!")) {
                WebElement btnOk = $("button.confirm");
                btnOk.click();
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            throw new Exception("error validate message");
        }

        driver.close();
    }

    public WebElement $(String selector) {
        WebElement element;
        if (selector.contains("#") && !selector.contains(">") || !selector.contains(".")) {
            selector = selector.replace("#", "");
            element = driver.findElement(By.id(selector));
        } else {
            element = driver.findElement(By.cssSelector(selector));
        }

        return element;
    }

    public List<WebElement> $(String selector, WebElement pattern) {
        if (pattern != null) {
            pattern.findElements(By.cssSelector(selector));
        }
        return driver.findElements(By.cssSelector(selector));
    }

    public void waitElementVisible(String selector) throws Exception {
        boolean sucess = false;
        try {
            WebElement element = $(selector);
            while (!element.isDisplayed()) {
                Thread.sleep(250);
            }
        } catch (Exception e) {
            while (!sucess) {
                Thread.sleep(250);
                List<WebElement> elements = $(selector,null);
                if(!(elements.size() == 0)){
                    sucess = true;
                }
            }
        }

    }

    public void fieldInvalid(WebElement selector) throws Exception {
        Thread.sleep(2000);
        if (selector.getCssValue("background-color").equals("rgba(254, 236, 235, 1)")) {
            throw new Exception("invalid entered characters");
        }
    }
}
