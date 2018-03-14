package com.kingja.contactssir;

import android.content.Loader;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/3/14 16:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Util {

    public static List<Contacts> getContacts(Cursor cursor) {
        PinyinTool pinyinTool = new PinyinTool();
        List<Contacts> contactsList = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Contacts contacts = new Contacts(pinyinTool.toPinYin(name), number, name);
            contactsList.add(contacts);
        }
        Collections.sort(contactsList, new SortByLetterComparator());
        for (Contacts contacts : contactsList) {
            Log.e("Util", "letter: " + contacts.getFirstLetter() + "name: " + contacts.getName() + "  number: " +
                    contacts.getNumber());
        }
        return contactsList;
    }
}
