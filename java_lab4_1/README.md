# Лабораторна робота 4.1 (Easy)

## Завдання:

**Реалізуйте функцію, яка перевіряє, чи дане слово є паліндромом (читається так само, як назад, так і вперед), використовуючи рядки Java. Не забувайте ігнорувати регістр і пробіли!**<br>
**Програма повинна мати такі функції:**

- Введення слова для перевірки
- Перевірка на паліндром

## Після реалізації перевірки на паліндром:

- Покрити тестами функціональність програми.

## Опис роботи:

1. Створення класу PalindromeChecker: Створено клас PalindromeChecker, який містить метод isPalindrome, що перевіряє,
   чи слово є паліндромом. Метод видаляє всі символи, окрім букв, та перевіряє рядок на паліндром, ігноруючи регістр символів та пробіли.
2. Створення класу Main: Створено клас Main, який містить метод main, що запускає програму в безкінечному циклі, де користувач може вводити слова 
   для перевірки на паліндром. Програма продовжує виконуватися, поки користувач не введе "exit".
3. Створення JUnit тестів: Написані JUnit тести у класі PalindromeCheckerTest для перевірки методу isPalindrome 
   на різних прикладах слів та фраз. Тести перевіряють різні випадки, включаючи паліндроми, не паліндроми, пусті рядки, одиничні символи та врахування пробілів та розділових знаків.
4. Виправлення помилок у тестах: Виявлена помилка у тесті, яка пов'язана з обробкою розділових знаків та регістру символів у методі isPalindrome. 
   Було внесено зміни до методу isPalindrome, щоб правильно врахувати ці аспекти при перевірці паліндромів.
5. Повторний запуск тестів: Після внесення змін у метод isPalindrome, JUnit тести запущені знову для перевірки коректності роботи програми.

# Висновок:

В ході цієї роботи я більш детально ознайомився з мовою програмування Java та принципами об'єктно-орієнтованого програмування.
Закріпив знання у створюванні класів та об'єктів, використовувати конструктори, методи та властивості об'єктів.


