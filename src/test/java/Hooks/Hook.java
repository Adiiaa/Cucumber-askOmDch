package Hooks;

import Factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {

    @Before
    public void setup(){
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
