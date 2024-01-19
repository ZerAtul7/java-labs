package com.kroll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Library {
    private final List<Item> items;
    private final Map<String, String> itemReaders;

    public Library() {
        this.items = new ArrayList<>();
        this.itemReaders = new HashMap<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public Map<String, String> getReaders() {
        return itemReaders;
    }

    public void addBook(Book book) {
        items.add(book);
        System.out.println("Книгу додано до бібліотеки.");
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
        } else {
            System.out.println("Предмети в бібліотеці:");
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    public Item findItemByTitle(String title) {
        for (Item item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null;
    }

    public void removeItemByIsbn(String isbn) {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getIdentifier().equals(isbn)) {
                iterator.remove();
                System.out.println("Предмет з ISBN " + isbn + " видалено.");
                return;
            }
        }
        System.out.println("Предмет з ISBN " + isbn + " не знайдено в бібліотеці.");
    }

    public void registerReader(String readerName) {
        System.out.println("Читач " + readerName + " зареєстрований.");
    }

    public void issueItemToReader(String itemIdentifier, String reader) {
        if (itemReaders.containsKey(itemIdentifier)) {
            System.out.println("Цей предмет вже видано читачеві " + itemReaders.get(itemIdentifier));
        } else {
            itemReaders.put(itemIdentifier, reader);
            System.out.println("Предмет видано читачеві " + reader);
        }
    }

    public void returnItemToLibrary(String itemIdentifier) {
        if (itemReaders.containsKey(itemIdentifier)) {
            itemReaders.remove(itemIdentifier);
            System.out.println("Предмет повернено в бібліотеку.");
        } else {
            System.out.println("Цей предмет не було видано читачеві.");
        }
    }

    public void displayAvailableItems() {
        System.out.println("Доступні предмети в бібліотеці:");
        for (Item item : items) {
            if (!itemReaders.containsKey(item.getIdentifier())) {
                System.out.println(item);
            }
        }
    }

    public void displayBorrowedItems() {
        System.out.println("Взяті предмети та їхніх читачі:");
        for (Map.Entry<String, String> entry : itemReaders.entrySet()) {
            String identifier = entry.getKey();
            String reader = entry.getValue();
            Item item = findItemByIdentifier(identifier);
            if (item != null) {
                System.out.println("Предмет: " + item.getTitle() + " (Ідентифікатор: " + identifier + ") взятий читачем " + reader);
            }
        }
    }

    public void addDVD(DVD dvd) {
        items.add(dvd);
        System.out.println("DVD додано до бібліотеки.");
    }

    public Item findItemByIdentifier(String identifier) {
        for (Item item : items) {
            if (item.getIdentifier().equals(identifier)) {
                return item;
            }
        }
        return null;
    }
}


