package pages;

import org.testng.annotations.Test;
import qaApiServices.appointments.CreateAppointment;
import qaApiServices.bloodPressure.CreateBp;
import com.github.javafaker.Faker;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import qaApiServices.patients.CreatePatients;
import utils.Date;

import java.util.List;

public class OverdueTabPage extends BasePage {

    private AppiumDriver driver;

    public OverdueTabPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBys({@FindBy(id = "patient_details")})
    private List<WebElement> patientDetail;

    private By callIcon = By.id("callButton");
    private By nameAndAge = By.id("patientNameTextView");
    private By patientbp = By.id("patientBPTextView");
    private By overdueDays = By.id("overdueDaysTextView");

    @FindBy(id = "phoneNumberTextView")
    private WebElement patientPhoneNumber;

    @FindBy(id = "actionsContainer")
    private WebElement overdueActionContainer;

    private By result = By.xpath("//android.widget.TextView[contains(@text,'Result of call')]");
    private By reasonAgreedToVisit = By.id("agreedToVisitTextView");
    private By reasonReminderLater = By.id("remindLaterTextView");
    private By reasonRemoveFromList = By.id("removeFromListTextView");


    @FindBy(xpath = "//android.widget.TextView[contains(@text,'No patients overdue')]")
    private WebElement noPatientsOverdueMessage;

    @FindBy(id = "appointmentreminder_done")
    private WebElement appointmentReminderDoneButton;


    @FindBys({
            @FindBy(className = "android.widget.RadioButton")
    })
    private List<WebElement> otherReason;

    @FindBy(id = "removeappointment_done_button")
    private WebElement doneButton;

    @FindBy(id = "removeappointment_toolbar")
    private WebElement removeAppointmentToolbar;
    private By reasonText = By.xpath("//android.widget.TextView[contains(@text,'Select a Reason')]");
    private By crossButton = By.className("android.widget.ImageButton");

    @FindBy(id = "phonemask_name")
    private WebElement phoneMaskName;

    @FindBy(id = "phonemask_phone_number")
    private WebElement phoneMaskphoneNumber;
    @FindBy(id = "phonemask_normal_call_button")
    private WebElement normalCallButton;

    @FindBy(id = "phonemask_secure_call_button")
    private WebElement secureCallButton;

    @FindBy(xpath = "//android.widget.TextView[contains(@text,'Secure calls hide your number and are toll free')]")
    private WebElement phoneMaskText;


    public void tapsOnPatientName(String patientName) {
        for (WebElement ele : patientDetail) {
            String[] split = ele.findElement(nameAndAge).getText().split(",");

            System.out.println("name" + split[0]);
            if (split[0].toUpperCase().contains(patientName.toUpperCase())) {
                ele.click();
                break;
            }
        }
    }

    public void verifiesPatientDetailExpeandedView() {
        Assert.assertTrue(patientPhoneNumber.isDisplayed());
        Assert.assertTrue(overdueActionContainer.findElement(result).isDisplayed());
        Assert.assertTrue(overdueActionContainer.findElement(reasonAgreedToVisit).isDisplayed());
        Assert.assertTrue(overdueActionContainer.findElement(reasonReminderLater).isDisplayed());
        Assert.assertTrue(overdueActionContainer.findElement(reasonRemoveFromList).isDisplayed());
    }

    public void isPatientPresent(String patientName) {
        String status = "false";

        for (WebElement ele : patientDetail) {
            String[] split = ele.findElement(nameAndAge).getText().split(",");
            if (split[0].toUpperCase().contains(patientName.toUpperCase())) {
                status = "true";
                Assert.assertTrue(ele.findElement(callIcon).isDisplayed());
                Assert.assertTrue(ele.findElement(patientbp).isDisplayed());
                Assert.assertTrue(ele.findElement(overdueDays).isDisplayed());
                break;
            }
        }
        Assert.assertEquals(status, "true", "patient name should be present in overdue list");

    }

    public void tapsOnAgreeToVisit() {
        overdueActionContainer.findElement(reasonAgreedToVisit).click();
    }

    public void isPatientNotPresent(String patientName) {
        String status = "true";

        if (patientDetail.size() == 0) {
            Assert.assertTrue(noPatientsOverdueMessage.isDisplayed());
        } else {
            for (WebElement ele : patientDetail) {
                String[] split = ele.findElement(nameAndAge).getText().split(",");
                if (split[0].contains(patientName)) {
                    status = "false";
                }
            }
        }
        Assert.assertEquals(status, "true", "patient name should not be displayed in overdue section");
    }

    public void tapsOnRemindToCallLater() {
        overdueActionContainer.findElement(reasonReminderLater).click();
    }

    public void tapsOnDoneButton() {
        appointmentReminderDoneButton.click();
    }

    public void tapsOnRemoveFormOverdueList() {
        overdueActionContainer.findElement(reasonRemoveFromList).click();
    }

    public void verfiyReasonScreen() {
        Assert.assertTrue(removeAppointmentToolbar.findElement(reasonText).isDisplayed());
        Assert.assertTrue(removeAppointmentToolbar.findElement(crossButton).isDisplayed());
        Assert.assertTrue(doneButton.isDisplayed());
        Assert.assertEquals(otherReason.size() , 7);
    }

    public void selectReason(String reason) {
        waitForElementToBeVisible(appointmentReminderDoneButton);
        driver.findElement(By.xpath("//android.widget.RadioButton[contains(@text,'" + reason + "')]")).click();
    }

    public void isPatientNotPresentInList(String patient) {
        isPatientNotPresent(patient);
    }

    public void createOverduePatientForTodayFromApi() {
        int dd = new Faker().random().nextInt(40, 90);
        new CreatePatients().createPatientWithBackDate(dd);
        new CreateBp().createBpWithBackDate(dd);

        new CreateAppointment().createAppointmentForOverduePatient(0);
    }

    public void tapsOnCallIcon(String patientName) {
        for (WebElement ele : patientDetail) {
            String[] split = ele.findElement(nameAndAge).getText().split(",");

            if (split[0].toUpperCase().contains(patientName.toUpperCase())) {
                ele.findElement(callIcon).click();
                break;
            }
        }
    }

    public void verifiesPopup() {
        Assert.assertTrue(phoneMaskName.isDisplayed());
        Assert.assertTrue(phoneMaskphoneNumber.isDisplayed());
        Assert.assertTrue(normalCallButton.isDisplayed());
        Assert.assertTrue(secureCallButton.isDisplayed());
        Assert.assertTrue(phoneMaskText.isDisplayed());
    }

    public void createOverduePatient() {
        int dd = new Faker().random().nextInt(40, 90);
        new CreatePatients().createPatientWithBackDate(dd);
        new CreateBp().createBpWithBackDate(dd);
        new CreateAppointment().createAppointmentForOverduePatient(dd);
    }

}
