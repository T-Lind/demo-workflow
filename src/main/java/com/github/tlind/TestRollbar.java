package com.github.tlind;
import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;
import com.rollbar.notifier.Rollbar;

public class TestRollbar {
    public static void main(String[] args) throws Exception {
        Rollbar rollbar = Rollbar.init(withAccessToken("put-token-here-from-rollbar-setup")
                .environment("qa")
                .codeVersion("1.0.0")
                .build());

        rollbar.log("Hello, Rollbar");

// call rollbar.close(true) before application exits to flush error message queue
        rollbar.close(true);
    }
}
