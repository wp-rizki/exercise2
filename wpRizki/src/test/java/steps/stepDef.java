package steps;


import function.userFunction;
import java.net.MalformedURLException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class stepDef {
    userFunction user = new userFunction();

    @Given("^User access Gist$")
    public void user_access() {
        System.out.println("True");
        user.createServer();
    }

    @Given("^User Edit an Existing Gist$")
    public void edit_gist() throws InterruptedException{
        user.editGist();
    }

    @Given("^User Delete an Existing Gist$")
    public void delete_gist() throws InterruptedException {
        user.deleteGist();
    }

    @Given("^When User Click See All of Your Gists$")
    public void all_gist() throws InterruptedException {
        user.viewAllGist();
    }

    @And("^User Login$")
    public void usrLogin() throws InterruptedException {
        user.login();
    }

    @And("^User Add New Public Gist$")
    public void createGist() throws InterruptedException {
        user.clickAdd();
        user.createGist();
        user.verifyGistExist();
    }

    @Then("^Verify That Gist Exist")
    public void verify_new_gist() throws InterruptedException {
        user.verifyGistExist();
    }

    @Then("^Verify That Gist Deleted")
    public void verify_delete_gist() throws InterruptedException {
        user.verifyDeleteGist();
    }


//    @Then("^End$")
//    public void end() {
//        //user.tearDown();
//        System.out.println("end");
//    }
}
