package com.example.thinhnbph36492_mob2041_ass.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thinhnbph36492_mob2041_ass.DAO.LoaiSachDAO;
import com.example.thinhnbph36492_mob2041_ass.DAO.ThanhVienDAO;
import com.example.thinhnbph36492_mob2041_ass.Fragment.Frag_QLThanhVien;
import com.example.thinhnbph36492_mob2041_ass.Model.LoaiSach;
import com.example.thinhnbph36492_mob2041_ass.Model.ThanhVien;
import com.example.thinhnbph36492_mob2041_ass.R;
import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {

    private ArrayList<ThanhVien> list;
    private Context context;
    Frag_QLThanhVien fragment;
    TextView tvMaTv, tvTenTv, tvNamSinh, tvGioiTinh;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, Frag_QLThanhVien fragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_rc_thanhvien, null);
        }
        final ThanhVien item = list.get(position);
        if (item != null) {

            // Kiểm tra giới tính từ cơ sở dữ liệu

            tvMaTv = v.findViewById(R.id.tvMaTv);
            tvMaTv.setText("Mã thành viên: " + item.getMaTV());
            tvTenTv = v.findViewById(R.id.tvTenTv);
            tvTenTv.setText("Tên thành viên: " + item.getHoTen());
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: " + item.getNamSinh());
            tvGioiTinh = v.findViewById(R.id.tvGioiTinh);
            LinearLayout listItemLayout = v.findViewById(R.id.linear1);

            String gioiTinh = item.getGioiTinh();

            if (gioiTinh != null) {
                tvGioiTinh.setText("Giới tính: " + gioiTinh);

                // Kiểm tra giới tính và đặt màu nền tương ứng
                if (gioiTinh.equalsIgnoreCase("Nam")) {
                    listItemLayout.setBackgroundColor(Color.GREEN);
                } else if (gioiTinh.equalsIgnoreCase("Nữ")) {
                    listItemLayout.setBackgroundColor(Color.RED);
                }
            } else {
                tvGioiTinh.setText("Giới tính: ");
            }

            // Kiểm tra giới tính

        }
        imgDel = v.findViewById(R.id.btn_remove);
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaTV()));
            }
        });
        return v;
    }
}
