# Demo Workflow for Java with Gradle
Author: Tiernan Lindauer

This is a demo workflow for Java with Gradle. It builds a simple Java application and runs unit tests.

Check out `./.github/workflows/commit-checks.yml` to see how it works. There's some helpful comments too.

To set up something like this in your own project, simply copy over the `.github/workflows` folder with the `commit-checks.yml` file in it.
The only Gradle dependency you need to add is `org.junit.jupiter:junit-jupiter` for the unit tests.

Source code is in `./src/main/java/com/github/tlind`, unit tests are under `./src/test/java`.

