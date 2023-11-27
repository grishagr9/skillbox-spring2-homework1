package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class WorkerContacts {

    @Value("${data.path}")
    private String filePath;

    private final InitContacts initContacts;

    private List<Contact> contactList;
    private String info = "Выберите действие:\n" +
            "1. Вывести все контакты\n" +
            "2. Добавить новый контакт\n" +
            "3. Удалить контакт по email\n" +
            "4. Сохранить имеющиеся контакты в файл\n" +
            "5. Выход";

    public WorkerContacts(InitContacts initContacts) {
        this.initContacts = initContacts;
    }

    public void startWork() throws IOException {
        System.out.println(info);
        contactList = initContacts.getContactList(filePath);
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println(info);
            int change = scanner.nextInt();
            switch (change){
                case 1:
                    printAllContacts();
                    continue;
                case 2:
                    addContact();
                    continue;
                case 3:
                    deleteContact();
                    continue;
                case 4:
                    saveToFile();
                    continue;
                case 5:
                    return;
            }
        }
    }

    private void deleteContact() {
        System.out.println("Введите email контакта");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        int index = -1;
        for (int i = 0; i <contactList.size(); i++) {
            if(contactList.get(i).getEmail().equals(email)){
                index = i;
                break;
            }
        }
        if(index == -1) {
            System.out.println("Контакт не найден");
            return;
        }
        contactList.remove(index);
        System.out.println("Контакт успешно удален");
    }

    private void printAllContacts(){
        System.out.println("Все контакты:");
        for (Contact contact:contactList) {
            System.out.println(contact);
        }
    }

    private void addContact(){
        System.out.println("Введите данные нового контакта в формате NAME;PHONE;EMAIL");
        Scanner scanner = new Scanner(System.in);
        String inputData = scanner.nextLine();

        String[] dataToContact = inputData.split(";");
        if(dataToContact.length != 3){
            System.out.println("Incorrect input data");
            return;
        }else{
            contactList.add(new Contact(dataToContact[0].trim(),dataToContact[1].trim(),dataToContact[2].trim()));
            System.out.println("Данные успешно добавлены!");
        }
    }

    private void saveToFile(){
        File file = new File(filePath);
        try (FileWriter fw = new FileWriter(file))
        {
            for(Contact contact:contactList) {
                fw.write(contact.toString());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
