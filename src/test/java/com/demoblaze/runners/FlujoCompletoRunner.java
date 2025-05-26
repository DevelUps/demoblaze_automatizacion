package com.demoblaze.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/flujo_completo.feature", glue = "com.demoblaze.stepdefinitions", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class FlujoCompletoRunner {
}