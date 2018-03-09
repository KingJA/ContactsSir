package sample.kingja.contactssir;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lv = findViewById(R.id.lv);
        IndexView indexView = findViewById(R.id.indexView);
        List<Contacts> contacts = queryContactPhoneNumber();
        final ContractsAdapter contractsAdapter = new ContractsAdapter(this, contacts);
        lv.setAdapter(contractsAdapter);
        indexView.setOnIndexSelectedListener(new IndexView.OnIndexSelectedListener() {
            @Override
            public void onIndexSelected(int index, String letter) {
                int position = contractsAdapter.getPositionForSection(letter.charAt(0));
                if (position != -1) {
                    lv.setSelection(position);
                }
            }

            @Override
            public void onIndexSelectedCompleted() {
            }
        });
    }

    private List<Contacts> queryContactPhoneNumber() {
        PinyinTool pinyinTool = new PinyinTool();
        List<Contacts> contactsList = new ArrayList<>();
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            // 取得联系人名字
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Contacts contacts = new Contacts(pinyinTool.toPinYin(name), number, name);
            Log.e(TAG, "contacts: "+contacts.toString() );
            contactsList.add(contacts);

        }
        Collections.sort(contactsList, new SortByLetterComparator());
        for (Contacts contacts : contactsList) {
            Log.e(TAG, "letter: " + contacts.getFirstLetter() + "name: " + contacts.getName() + "  number: " +
                    contacts.getNumber());
        }
        return contactsList;
    }
}
