# spring-and-bsaf-kotlin

This is a slightly extended Kotlin version of the 
[spring-and-bsaf](https://github.com/dykstrom/spring-and-bsaf) example project.

The code has been rewritten in Kotlin, and Spring has been upgraded to version 5, 
with built-in Kotlin support.

The main class now uses the "functional bean declaration DSL" of Spring 5 to create
Spring beans, and the @Configuration and @Component annotations are gone.

Finally, the Swing UI is created using a simple Swing builder DSL, see
[SwingDsl](https://github.com/dykstrom/spring-and-bsaf-kotlin/blob/master/src/main/kotlin/se/dykstrom/spring/SwingDsl.kt).
The Swing builder DSL is modelled after [kotlinx.html](https://github.com/Kotlin/kotlinx.html). 
Below is an example of how to create a panel with a single button using 
the Swing builder DSL:

```kotlin
panel {
    layout = BorderLayout()
    border = EmptyBorder(100, 150, 100, 150)

    button {
        +BorderLayout.CENTER
        action = actions.get("openDialog")
    }
}
```
