package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.QaApiCallsPage;
import qaApiServices.appointments.CreateAppointment;
import utils.RandomValue;

public class QaAPICallsSteps extends BaseSteps {
    @Given("^(\\w+) registers new user from api$")
    public void userRegistersNewUser(String User) {
        new QaApiCallsPage(getDriverInstanceFor(User)).registerNewUser();
    }

    @And("^(\\w+) registers new patient without phonenumber from api$")
    public void userRegistersNewPatientWithoutPhonenumberThroughAPI(String User) {
        new QaApiCallsPage(getDriverInstanceFor(User)).registerNewPatientWithoutPhoneNumber();
    }

    @Given("^(\\w+) registers new patient with bp from api$")
    public void userRegisterNewPatientWithBp(String User) {
        new QaApiCallsPage(getDriverInstanceFor(User)).registerNewPatientWithBp();
    }

    @And("^(\\w+) registers new patient without bp from api$")
    public void userRegisterNewPatientWithoutBpFromApi(String User) {
        new QaApiCallsPage(getDriverInstanceFor(User)).registerNewPatientWithoutBp();
    }

    @And("^(\\w+) registers multiple patients with same phoneNumber from api$")
    public void userRegistersMultiplePatientWithSamePhoneNumberFromApi(String User) {
        String phoneNumber = RandomValue.getRandomPhoneNumber();
        new QaApiCallsPage(getDriverInstanceFor(User)).registerMultiplePatientWithDuplicatePhoneNumber(2, phoneNumber);
    }

    @And("^(\\w+) registers a patient with multiple Bps$")
    public void userRegistersAPatientWithMultipleBpS(String User) {
        new QaApiCallsPage(getDriverInstanceFor(User)).registerNewPatientWithListOfBps(1, 2);
    }

    @And("^(\\w+) registers new patient without phonenumber and bp from api$")
    public void userRegistersNewPatientWithoutPhonenumberAndBpFromApi(String User) {
        new QaApiCallsPage(getDriverInstanceFor(User)).registerNewPatientWithoutPhoneNumberAndBP();
    }

    @And("^(\\w+) get all patient info$")
    public void userGetAllPatientInfo(String User) {
        int allScheduledAppointment = new CreateAppointment().getAllScheduledAppointment();
    }

    @And("^(\\w+) registers new patient with bp passport from api$")
    public void userRegistersNewPatientWithBpPassportFromApi(String User) {
        new QaApiCallsPage(getDriverInstanceFor(User)).registerNewPatientWithBpPassportFromApi();
    }

    @And("^(\\w+) verifies reminder consent value in api service for (.*)")
    public void userVerifiesReminderConsentValueInApiServiceForEnabled(String User,String value) {
        new QaApiCallsPage(getDriverInstanceFor(User)).verifyReminderConsentAttribute(value);
    }

    @And("^(\\w+) registers multiple patient (.*) from api$")
    public void userRegistersMultiplePatientCountFromApi(String User,int count) {
        new QaApiCallsPage(getDriverInstanceFor(User)).registerMultiplePatients(count);
    }

    @And("^(\\w+) verifies patient info get synced to server$")
    public void userVerifiesPatientInfoGetSyncedToServer(String User) {
        new QaApiCallsPage(getDriverInstanceFor(User)).isPatientInfoSyncToServer();
    }
}
