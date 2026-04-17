# Week 3 — Day 2

## Overview
This folder contains Day 2 exercises for Week 3. The main focus is working with simple browser-launcher classes and practice/homework Java problems. Examples include small utility classes to demonstrate launching different browsers and a `TestBrowser` harness that exercises those classes. The `HomeAssignment` subfolder contains collection/string algorithm exercises and a small inheritance example.

## Files
- Browser.java: Base or generic browser launcher helper used in examples.
- Chrome.java: Chrome-specific launcher/example class.
- Edge.java: Edge-specific launcher/example class.
- Safari.java: Safari-specific launcher/example class.
- TestBrowser.java: Example/test harness that demonstrates using the browser classes. Run this to see how browser classes are instantiated and used.
- HomeAssignment/: Folder with homework exercises (see details below).

## HomeAssignment (summary)
The `HomeAssignment` folder contains small practice programs grouped by topic:
- `AnagramCheck.java` — checks whether two strings are anagrams.
- `ChangeOddIndex.java` — manipulates characters at odd indices (exercise variations).
- `CountCharacter.java` — counts occurrences of characters in a string.
- `inheritance/` — small inheritance demo (e.g., `Button`, `CheckBoxButton`, `Elements`, `RadioButton`, `TextField`, `WebElement`).
- `removeDuplicate/RemoveDuplicateWords.java` — removes duplicate words from text.
- `reverseOddwords/` — contains `ReverseOddWords.java` and `ReverseString.java`.
- `singlelevelInhersistance/ExecuteTest.java` — small single-level inheritance execution example.

## Build
This project is managed with Maven. From the project root (`seleniumtestleaf`) you can build the project using:

```bash
mvn clean package
```

This will compile the sources and place compiled classes under `target/classes`.

## Run (examples)
After building, run any class that contains a `main` method from the project root. Replace the example class name with the one you want to run.

Example: run the `TestBrowser` harness:

```bash
java -cp target/classes Week3.Day2.TestBrowser
```

Example: run the single-level inheritance example:

```bash
java -cp target/classes Week3.Day2.HomeAssignment.singlelevelInhersistance.ExecuteTest
```

If a class is inside nested packages (e.g., `Week3.Day2.HomeAssignment.removeDuplicate.RemoveDuplicateWords`), call it with its full package-qualified name:

```bash
java -cp target/classes Week3.Day2.HomeAssignment.removeDuplicate.RemoveDuplicateWords
```

Note for Windows: the classpath separator is `;`. If you need to include external jars, add them to the classpath accordingly.

## Notes & Tips
- You can also run classes directly from an IDE (IntelliJ IDEA, Eclipse) by right-clicking the class with `main` and choosing Run.
- Ensure you have a JDK installed and `JAVA_HOME`/`PATH` set. This workspace was tested with JDK 26 but any recent JDK should work.
- If you prefer Maven-run style (requires `exec-maven-plugin` in `pom.xml`), you can run with the `exec:java` goal and provide `-Dexec.mainClass`.

---
If you want, I can also add individual README files inside the `HomeAssignment` subfolders with detailed explanations and sample inputs/outputs.