package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"StepDefinitions","Hooks"},
        plugin = {"pretty"},
        monochrome = true,
        tags = "@cart"
)


public class TestRunner {

}
