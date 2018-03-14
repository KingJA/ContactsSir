package sample.kingja.contactssir;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kingja.contactssir.Contacts;
import com.kingja.contactssir.ContactsLoadTask;
import com.kingja.contactssir.IndexView;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.tv);
        ContactsLoadTask contactsLoadTask = new ContactsLoadTask(this,new ContactsLoadTask.OnContactsLoadListener() {
            @Override
            public void onLoadStart() {
                Log.e(TAG, "开始: " );
                tv.setText("bbb");
            }

            @Override
            public void onLoadSuccess(List<Contacts> contacts) {
                Log.e(TAG, "结束: " );
                final ListView lv = findViewById(R.id.lv);
                IndexView indexView = findViewById(R.id.indexView);
                final ContractsAdapter contractsAdapter = new ContractsAdapter(MainActivity.this, contacts);
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
        });
        contactsLoadTask.execute();

    }

}
