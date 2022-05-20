package config;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseSetup {

    @BeforeAll
    static void setUp() {
        System.out.println("BEFORE ALL");
        Configuration.holdBrowserOpen = false;
        Configuration.baseUrl = "https://github.com";
    }
}
