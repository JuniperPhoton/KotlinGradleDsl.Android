# KotlinGradleDsl.Android

Sample project to desmonstrate how to use Gradle Kotlin DSL in Android project, including:

- Use extra properties;
- Apply from other gradle file;
- Use manifestPlaceholders
- Change APK name of output files;
- Flavor dimensions and product flavors;
- SourceSets altered;
- Java 8 support;
- Read local properties;
- SigningConfigs;

## Build the app

Use this command:

```
./gradlew clean :app:assembleAlphaDebug
```

to build an APK.

## Common issues

> 1. The `android{}` block is not recognized by IDE.

Try to do this:

- Close the IDE
- Delete `.gradle` and `.idea` folder in the root of the project
- Launch the IDE
- (Optional) Invalidate and Restart through "File" menu.

> 2. The issue above is still on

If you have modified the content of gradle.kts file, make sure you are doing it right and checkout the "Build" window to see the issues.
