package sample.kingja.contactssir;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.kingja.contactssir.Contacts;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/24 10:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContractsAdapter extends BaseAdapter implements SectionIndexer {
    private Context context;
    private List<Contacts> cities;


    public ContractsAdapter(Context context, List<Contacts> list) {
        this.context = context;
        this.cities = list;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_index, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.tv_firstLetter = (TextView) convertView.findViewById(R.id.tv_firstLetter);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(cities.get(position).getName());
        viewHolder.tv_number.setText(cities.get(position).getNumber());
        viewHolder.tv_firstLetter.setText(cities.get(position).getFirstLetter());
        int currentFirstLetter = getSectionForPosition(position);
        if (position == getPositionForSection(currentFirstLetter)) {
            viewHolder.tv_firstLetter.setVisibility(View.VISIBLE);
            viewHolder.tv_firstLetter.setText(cities.get(position).getFirstLetter());
        } else {
            viewHolder.tv_firstLetter.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = cities.get(i).getFirstLetter();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return cities.get(position).getFirstLetter().charAt(0);
    }

    class ViewHolder {
        public TextView tv_firstLetter;
        public TextView tv_name;
        public TextView tv_number;
    }
}
