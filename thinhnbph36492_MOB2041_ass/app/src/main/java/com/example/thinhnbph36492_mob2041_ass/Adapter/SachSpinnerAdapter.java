package com.example.thinhnbph36492_mob2041_ass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thinhnbph36492_mob2041_ass.Model.Sach;
import com.example.thinhnbph36492_mob2041_ass.R;

import java.util.ArrayList;


public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private ArrayList<Sach> list;
    private Context context;
    TextView tvMaSach, tvTenSach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_sach_spinner, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSachSp);
            tvMaSach.setText(item.getMaSach() + ". ");
            tvTenSach = v.findViewById(R.id.tvTenSachSp);
            tvTenSach.setText(item.getTenSach());

        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_sach_spinner, null);
        }
        final Sach item = list.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSachSp);
            tvMaSach.setText(item.getMaSach() + ". ");
            tvTenSach = v.findViewById(R.id.tvTenSachSp);
            tvTenSach.setText(item.getTenSach());
        }
        return v;
    }
}
