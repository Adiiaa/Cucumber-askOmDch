package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Browse-By-Categories.feature",
        glue = {"StepDefinitions","Hooks","config","Factory","pages"},
        plugin = {"pretty"},
        monochrome = true

)


public class TestRunner {

}
