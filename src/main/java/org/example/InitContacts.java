package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitContacts {

    public InitContacts(){}

    public List<Contact> getContactList(String filePath) throws IOException {
        if(filePath == null){
            return new ArrayList<>();
        }
        List<Contact> contactList = new ArrayList<>();

        File file = new File(filePath);
        FileWriter writer = new FileWriter(filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> a = reader.lines().toList();
            for (String item: a) {
                String[] string = item.split(";");
                if(string.length == 3) {
                    Contact cur = new Contact();
                    cur.setFullName(string[0]);
                    cur.setPhoneNumber(string[1]);
                    cur.setEmail(string[2]);
                    contactList.add(cur);
                }else {
                    System.out.println("incorrect data in input file");
                    continue;
                }
            }
        }catch (IOException e) {
            System.out.println("Error in InitContacts::getContactsList()");
            e.printStackTrace();
        }

        return contactList;
    }
}
