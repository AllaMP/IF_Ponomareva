## Автотесты Jira Ifellow

Этот проект содержит автотесты по двум задачам: "Reqres", "RikAndMorty". 

## Используемые технологии

- Язык программирования: Java 17
- Версия: 17.0.12 (LTS)
- Сборка и управление зависимостями: Apache Maven
- Фреймворк для тестирования: JUnit 5
- Автоматизация веб-тестов: Cucumber
- Фреймворк для отчетов: Allure Framework

## Браузеры и драйверы

- Mozilla Firefox
    - version **135.0**
    - geckodriver **0.35.0**
- Google Chrome
    - version **133.0.6943.54s**
    - chromedriver **33.0.6943.53**

## Настройка окружения

1. Убедитесь, что у вас установлены:
- Java 17
- Apache Maven
- Браузеры (Firefox и Chrome) и соответствующие драйверы (Geckodriver и Chromedriver).
2. Клонируйте репозиторий:
````bash
  git clone https://github.com/AllaMP/IF_Ponomareva.git
````
3. Перейдите в директорию проекта
````bash
   cd IF_Ponomareva
````
4. Запустите тесты (см. раздел "Запуск тестов и формирование отчетов").


## Структура проекта
Проект имеет следующую структуру:
1. src/main/java/reqres - тестовые данные по первой задаче
   - src/main/java/reqres/pojoObject - содержит описание объектов
   - src/main/java/reqres/reqresApiClient - описание API
   - src/main/java/reqres/steps - содержит шаги теста
   - src/testAPI/java/reqres/testAPI - содержит файл запуска тестов
2. src/main/java/rickAndMorty - тестовые данные по второй задаче
   - src/main/java/rickAndMorty/api - описание API
   - ssrc/main/java/rickAndMorty/models - содержит описание объектов
   - src/main/java/rickAndMorty/steps - содержит шаги теста
   - src/testAPI/java/rickAndMorty/characterTest - содержит файл запуска тестов
   - src/testAPI/resources/rickAndMorty/feature - описание фичи

## Сценарии тестирования
1. Создание пользователя с данными из JSON-файла
2. Создание пользователя с измененными данными
2. Получение информации о персонаже Morty Smith
3. Сравнение персонажа Morty Smith с последним персонажем из последнего эпизода


## Запуск тестов и формирование отчетов

- для запуска тестов выполнить команду:
````bash 
    mvn clean testAPI
````
- для формирования отчета выполнить команду:
````bash
    mvn allure:report
````
- для просмотра отчета в браузере выполнить команду:
````bash 
    mvn allure:serve
````

____
Тестировщик: Пономарева Алла

Репозиторий: https://github.com/AllaMP/IF_Ponomareva.git

