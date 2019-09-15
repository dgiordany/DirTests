Документация
--
Не особо требуется.
Чтобы запустить тесты, необходимо установить консольную утилиту [Apache Maven](https://maven.apache.org/download.cgi) последней версии.

Либо установить [Eclipse IDE](https://www.eclipse.org/downloads/) или [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) (в последнее maven входит по умолчанию).

Запуск тестов
--
Перейти в корневую директорию проекта, где расположен файл pom.xml. 

Чтобы запустить тесты, необходимо выполнить команду
```
mvn clean test -B -f pom.xml 
```