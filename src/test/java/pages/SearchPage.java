package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import qaApiServices.patients.CreatePatients;
import qaApiServices.patients.GetPatientInfo;
import qaApiServices.patients.PatientGetRequestResponse;
import qaApiServices.user.RegisterUser;
import qaApiServices.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchPage extends BasePage {
    AppiumDriver driver;

    public SearchPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "patientNameEditText")
    private WebElement searchPatientName;

    @FindBy(id = "patientsearch_search")
    private WebElement NextButton;


    @FindBy(id = "patients_search_patients")
    private WebElement searchPatientTextBox;

    @FindBy(id = "searchresults_new_patient")
    private WebElement registerAsNewPatientButton;

    @FindBy(id = "searchresults_new_patient_rationale")
    private WebElement registerPatientLabel;

    @FindBy(id = "searchresults_empty_state_text")
    private WebElement emptySearchResult;

    @FindBys({
            @FindBy(id = "patientsearch_header")
    })
    private List<WebElement> header;


    @FindBys({
            @FindBy(id = "patientNameAgeGenderLabel")
    })
    private List<WebElement> patientList;


    private void searchPatient(String patientName) {
        waitForElementToBeVisible(searchPatientName);
        searchPatientName.sendKeys(patientName);
        NextButton.click();
    }

    public void searchForRegisteredPatientWithBpInfo(String patientName) {
        searchPatient(patientName);

        String status = "false";
        for (WebElement ele : header) {
            if (ele.getText().contains("Has visited")) {
                status = "true";
            }
        }
        Assert.assertEquals(status, "true", "has visited section isn't displayed");
        Assert.assertTrue(registerAsNewPatientButton.isDisplayed());
    }

    //    This method should be used for
//    invalid search or for new patient
    public void searchForPatientName(String patientName) {
        searchPatient(patientName);
        Assert.assertEquals(emptySearchResult.getText(), "No patients match");
        Assert.assertEquals(registerPatientLabel.getText(), "Patient is not registered");
        Assert.assertTrue(registerAsNewPatientButton.isDisplayed());
    }

    public void searchForRegisteredPatientWithoutBPInfo(String patientName) {
        searchPatient(patientName);

        String status = "false";
        for (WebElement ele : header) {
            if (ele.getText().equals("Other Results")) {
                status = "true";
            }
        }
        Assert.assertEquals(status, "true", "other result section isn't displayed");
        Assert.assertTrue(registerAsNewPatientButton.isDisplayed());
    }

    public void tapsOnRegisteredPatientTab() {
        registerAsNewPatientButton.click();
    }

    public void selectsPatientFromSearchList(String patientName) {
        for (WebElement ele : patientList) {

            String[] str = ele.getText().split(",");
            String name = str[0].toUpperCase();

            if (name.contains(patientName.toUpperCase())) {
                ele.click();
            } else {
                Assert.fail("Patient Name is not displayed in search list");
            }
        }
    }

    public void tapsOnSearchTextBox() {
        searchPatientTextBox.click();
    }

    public void verifiesAlphabeticalLog() {

        new RegisterUser().registerNewUser();
        List<String> allPatientsName = new GetPatientInfo().getAllPatientsName();
        List<String> expectedPatientNameSortedList = allPatientsName.stream().sorted().collect(Collectors.toList());

        List<String> actualpatientName = new ArrayList<>();
        int count = 0;

        hideKeyboard();

        while (count <= allPatientsName.size()) {
            for (WebElement p : patientList) {
                actualpatientName.add(p.getText().split(",")[0].toUpperCase());
                count++;
            }
            scrollDown();
        }

        expectedPatientNameSortedList.equals(actualpatientName);
    }
}
