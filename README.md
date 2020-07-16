# QA_MacPaw
Instruments: Java, Selenium WebDriver + TestNg
Temp mail api - post-shift.ru

Download Chrome WebDriver, paste it to folder "C:\WebDriver\bin\chromedriver.exe" or change in code to yours
and run
mvn test

Важно: Розетка больше не шлет письмо с урлом подтверждения. В замен этого они остылают код подтверждения, который я достаю из письма и вставляю в поле. Но, скорее всего, домен временной почты они научились распознавать, поэтому подтвердить мыло, как написано в задании не вышло. Но flow рабочий. 
