# Техническое задание (ТЗ) для проекта “Электронный дневник для школьников начальных классов” 
## Команда: Овчинников Никита, Петрушина Надежда

### Введение
Проект "Электронный дневник для школьников начальных классов" предназначен для автоматизации процесса ведения учебной документации в образовательных учреждениях. 
Он позволит учителям, ученикам и родителям отслеживать успеваемость и другую важную информацию в удобном формате.

### Инструкция к запуску
Для упрощения развертывания и управления приложением «Школьный дневник» используется Docker. 
Docker позволяет создать контейнер, который включает в себя все необходимые компоненты для работы приложения.

Мы реализовали DockerFile. Этот файл содержит инструкции для сборки образа контейнера.
Для того чтобы создать докер образ приложения, сперва необходимо выполнить команду `mvn package`.

После успешной компиляции необходимо поднять все контейнеры в файле compose.yml.
После запуска контейнеров приложение будет доступно по адресу: «http://localhost:8080».