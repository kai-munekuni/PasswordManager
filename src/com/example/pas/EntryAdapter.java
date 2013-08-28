package com.example.pas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EntryAdapter extends ArrayAdapter<Entry> {

    private Context context;
    private List<Entry> items;

    public EntryAdapter(Context context, int resId, List<Entry> items) {
        super(context, resId, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        View rowView;
        if(contextView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row, parent, false);
        } else {
            rowView = contextView;
        }
        TextView nameTextView = (TextView) rowView.findViewById(R.id.nameTextView);
        TextView passwordTextView = (TextView) rowView.findViewById(R.id.passwordTextView);
        Entry entry = items.get(position);
        nameTextView.setText(entry.getServiceName());
        passwordTextView.setText(entry.getPassword());
        rowView.setOnClickListener(new RowViewClickListener(entry));

        if(entry.isSelected()) {
            rowView.setBackgroundColor(0x660000FF);
        } else {
            rowView.setBackgroundColor(0);
        }

        return rowView;
    }

    private class RowViewClickListener implements View.OnClickListener {
        private Entry entry;

        public RowViewClickListener(Entry entry) {
            this.entry = entry;
        }

        @Override
        public void onClick(View v) {
            if(entry.isSelected()) {
                entry.setSelected(false);
            } else {
                for(Entry e: items) {
                    e.setSelected(false);
                }
                entry.setSelected(true);
            }
            notifyDataSetChanged();
        }
    }
}