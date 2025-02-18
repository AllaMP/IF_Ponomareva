# language: ru
Функция: Создание новой задачи типа bug

  Сценарий: Переход в проект 'Тест'
    Дано авторизовываюсь на странице входа
    Когда кликаю на вкладку 'Проекты'
    Тогда перехожу в проект 'Тест'

  Сценарий: Создание новой задачи типа bug
    Дано авторизовываюсь на странице входа
    Когда кликаю на вкладку 'Проекты'
    Тогда перехожу в проект 'Тест'
    И сохраняю количество тестов до создания нового
    Дано кликаю на кнопку создания задачи
    И заполняю поле 'Тема'
    И выбораю 'Seriousnes'
    И нажимаю кнопку 'Визуальный'
    И нажимаю кнопку 'Визуальный2'
    И заполняю поле 'Описание'
    И заполняю поле 'Описание2'
    И выбираю Fix 'Versions'
    И заполняю поле 'Labels'
    И выбираю ссылку на 'Epic'
    И выбираю 'Спринт'
    И кликаю кнопку 'Создать'
    И кликаю на url нового теста
    И перехожу обратно на страницу тестов
    И сравниваю количество тестов после создания нового
    И нажимаю кнопку 'В работе'
    И нажимаю кнопку 'Бизнес-процесс'
    И выбираю 'Выполнено'


