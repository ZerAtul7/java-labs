package com.kroll;

import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Оберіть опцію:");
            System.out.println("1. Додати нову книгу в бібліотеку");
            System.out.println("2. Показати всі предмети (книги або DVD) в бібліотеці");
            System.out.println("3. Шукати предмет (книгу або DVD) за назвою");
            System.out.println("4. Видалити предмет (книгу або DVD) за ISBN");
            System.out.println("5. Реєструвати читача");
            System.out.println("6. Видавати предмет читачеві");
            System.out.println("7. Повернути предмет в бібліотеку");
            System.out.println("8. Показувати список доступних предметів (книги або DVD)");
            System.out.println("9. Показувати список взятих предметів та їхніх читачів (книги або DVD)");
            System.out.println("10. Додати новий DVD");
            System.out.println("11. Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера

            switch (choice) {
                case 1:
                    System.out.println("Введіть назву книги:");
                    String bookTitle = scanner.nextLine();
                    System.out.println("Введіть автора книги:");
                    String bookAuthor = scanner.nextLine();
                    System.out.println("Введіть номер ISBN книги:");
                    String bookIsbn = scanner.nextLine();
                    System.out.println("Введіть рік видання книги:");
                    int bookYear = scanner.nextInt();
                    scanner.nextLine(); // Очищення буфера
                    Book book = new Book(bookTitle, bookAuthor, bookIsbn, bookYear);
                    library.addBook(book);
                    break;

                case 2:
                    library.displayItems();
                    break;

                case 3:
                    System.out.println("Введіть назву предмету для пошуку:");
                    String searchTitle = scanner.nextLine();
                    Item foundItem = library.findItemByTitle(searchTitle);
                    if (foundItem != null) {
                        System.out.println("Знайдено предмет за назвою:");
                        System.out.println(foundItem);
                    } else {
                        System.out.println("Предмет за заданою назвою не знайдено.");
                    }
                    break;

                case 4:
                    System.out.println("Введіть ISBN предмету для видалення:");
                    String isbnToRemove = scanner.nextLine();
                    library.removeItemByIsbn(isbnToRemove);
                    break;

                case 5:
                    System.out.println("Введіть ім'я читача:");
                    String readerName = scanner.nextLine();
                    library.registerReader(readerName);
                    break;

                case 6:
                    System.out.println("Видавання предмету читачеві. Введіть ISBN предмету:");
                    String itemToIssue = scanner.nextLine();
                    System.out.println("Введіть ім'я читача:");
                    String reader = scanner.nextLine();
                    library.issueItemToReader(itemToIssue, reader);
                    break;

                case 7:
                    System.out.println("Повернення предмету в бібліотеку. Введіть ISBN предмету:");
                    String itemToReturn = scanner.nextLine();
                    library.returnItemToLibrary(itemToReturn);
                    break;

                case 8:
                    library.displayAvailableItems();
                    break;

                case 9:
                    library.displayBorrowedItems();
                    break;

                case 10:
                    System.out.println("Введіть назву DVD:");
                    String dvdTitle = scanner.nextLine();
                    System.out.println("Введіть режисера DVD:");
                    String dvdDirector = scanner.nextLine();
                    System.out.println("Введіть рік випуску DVD:");
                    String dvdReleaseYear = scanner.nextLine();
                    System.out.println("Введіть ідентифікатор DVD:");
                    String dvdIdentifier = scanner.nextLine();
                    DVD dvd = new DVD(dvdTitle, dvdDirector, dvdReleaseYear, dvdIdentifier);
                    library.addDVD(dvd);
                    break;

                case 11:
                    System.out.println("Дякуємо, що скористалися нашою бібліотекою.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
