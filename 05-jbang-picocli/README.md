# rewrite the cat terminal application using jbang
# Steps

## 1 - cat

- show cat terminal application

## 2 - init & edit

- `jbang init --template=cli Cat.java`
- `jbang edit Cat.java`

## 3 - https://picocli.info/

- show file content
- show picocli

## 4 - add cat logic

- change parameter to path
- `Files.readAllLines(Path.of(path)).forEach(IO::println);`

## 5 - add -n, --number parameter

- `@Option(names = { "-n", "--numbers" }, description="display line numbers") private boolean numbers;`
- change for loop
    - print counter if option set: `if(numbers) IO.print((i + 1) + "  ");`