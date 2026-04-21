# Steps

## 1 - JavaScript.java

- create a file with the following content
```java
public class JavaScript{
    public static void main(String args[]) {
        System.out.println("Hello World!");
    }
}
```
- compile it using `javac JavaScript.java`
- show `JavaScript.class` file
- run it using `java JavaScript`

## 2 - JavaScript.java

- run it using `java JavaScript.java`

## 3 - JavaScript

- add `#!/usr/bin/env -S java --source 25` to the first line of the file
- remove `.java` file ending
- make it executable `chmod +x JavaScript` 
- run it using `./JavaScript`

## 4 - JavaScriptMinimal

- remove class declaration
- change method declaration to `void main()`
- change `System.out.println` to `IO.println`

# 5 - JavaScriptMinimalPersonal

- add `var in = IO.readln("")` to make greeting personal

# 5 - Move onto path

- `sudo mv JavaScriptMinimalPersonal /usr/local/bin/`
- run `JavaScriptMinimalPersonal`