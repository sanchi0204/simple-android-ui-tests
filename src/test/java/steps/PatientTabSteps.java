package steps;

import com.embibe.optimus.utils.ScenarioContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.PatientTabPage;
import utils.RandomValue;
import utils.ScenarioContextKeys;

public class PatientTabSteps extends BaseSteps {
    @Then("^(\\w+) verifies Patient tab$")
    public void userVerifiesPatientTab(String User) throws Throwable {
        new PatientTabPage(getDriverInstanceFor(User)).verifyPatientTab();
    }

    @Then("^(\\w+) searches for unregistered Patient$")
    public void userPerformSearchForUnregisteredPatient(String User) {
        new PatientTabPage(getDriverInstanceFor(User)).searchForUnRegisteredPatient();
    }

    @Then("^(\\w+) searched for Registered Patient$")
    public void userSearchedForRegisteredPatient(String User) {
        String patientName=ScenarioContext.getData("User",ScenarioContextKeys.PATIENT_NAME);
        new PatientTabPage(getDriverInstanceFor(User)).searchForRegisteredPatient(patientName);
    }

    @Then("^(\\w+) searched for Registered Patient without BP info$")
    public void userSearchedForRegisteredPatientWithoutBPInfo(String User) {
        String patientName=ScenarioContext.getData("User",ScenarioContextKeys.PATIENT_NAME);
        new PatientTabPage(getDriverInstanceFor(User)).searchForRegisteredPatient(patientName);
    }
}
