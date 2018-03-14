package com.kingja.contactssir;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/3/14 17:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsLoadTask extends AsyncTask<Void, Void, Cursor> {
    private Context context;
    private OnContactsLoadListener onContactsLoadListener;

    public ContactsLoadTask(Context context, OnContactsLoadListener onContactsLoadListener) {
        this.onContactsLoadListener = onContactsLoadListener;
        this.context = context;
    }

    private static final String TAG = "ContactsLoadTask";

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "onPreExecute: ");
        if (onContactsLoadListener != null) {
            Log.e(TAG, "onContactsLoadListener != null: ");
            onContactsLoadListener.onLoadStart();
        }
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
        Log.e(TAG, "doInBackground: ");
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        return cursor;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        Log.e(TAG, "onPostExecute: ");
        List<Contacts> contacts = Util.getContacts(cursor);
        if (onContactsLoadListener != null) {
            onContactsLoadListener.onLoadSuccess(contacts);
        }
    }

    public interface OnContactsLoadListener {
        void onLoadStart();

        void onLoadSuccess(List<Contacts> contacts);
    }

}
