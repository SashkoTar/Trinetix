Trinetix
========

Trinetix test task

Краткое описание настройки

1) Клонируйте репозиторий
2) Выполнить команду mvn clean install
3) Создать базу данных в  MySql
4) Запустить скрипт script.sql
5) Изменить настройки бина dataSource в файле http-servlet.xml
6) Выложить war в Tomcat
7) http://localhost:8080/trinetix/employee/list


Вариант XML-файла для загрузки

<org.trinetix.dto.Employee>
  <Id>30</Id>	                              <!-- Идентификатор сотрудника, целое число -->
  <firstName>FIRST_NAME</firstName>
  <lastName>LAST_NAME</lastName>
  <dateOfBirth>1926-10-12</dateOfBirth>
  <startDate>2014-01-12</startDate>  
  <title>Manager</title>                    <!-- Или другое название позиции сотрудника -->
  <comments>Here is comments</comments>     <!-- Комментарии -->
</org.trinetix.dto.Employee>
