package com.sparta.jn.starwarsapitestingframework.stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
    @Before("@AllPeople")
    public void beforeScenario() {
        PeopleStepDef peopleStepDef = new PeopleStepDef();
        if (PeopleStepDef.MAX_PEOPLE == 0) {
            peopleStepDef.user_get_max_number_of_people();
        }
    }
}
