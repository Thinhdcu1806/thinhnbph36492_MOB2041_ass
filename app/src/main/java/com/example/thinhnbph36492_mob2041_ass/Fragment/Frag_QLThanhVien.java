package com.example.thinhnbph36492_mob2041_ass.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.thinhnbph36492_mob2041_ass.Adapter.ThanhVienAdapter;
import com.example.thinhnbph36492_mob2041_ass.DAO.ThanhVienDAO;
import com.example.thinhnbph36492_mob2041_ass.Model.ThanhVien;
import com.example.thinhnbph36492_mob2041_ass.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frag_QLThanhVien extends Fragment {
    ListView lvThanhVien;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    Spinner spinnerGioiTinh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_qlthanhvien, container, false);
        lvThanhVien = v.findViewById(R.id.lvThanhVien);
        fab = v.findViewById(R.id.fltAdd);
        dao = new ThanhVienDAO(getActivity());
        capNhatTv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
                //them
            }
        });

        lvThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(), 1);
                //update
                return false;
            }
        });
        return v;
    }

    void capNhatTv() {
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(), this, list);
        lvThanhVien.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        // Su dung Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không? ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dao.delete(Id);
                capNhatTv();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanhvien);
        edMaTV = dialog.findViewById(R.id.edtMaTV_itemAddThanhVien);
        edTenTV = dialog.findViewById(R.id.edtTenTV_itemAddThanhVien);
        edNamSinh = dialog.findViewById(R.id.edtNamSinh_itemAddThanhVien);
        spinnerGioiTinh = dialog.findViewById(R.id.spGioiTinh);
        btnCancel = dialog.findViewById(R.id.btnHuy);
        btnSave = dialog.findViewById(R.id.btnLuu);

        // Kiểm tra type (0 - Insert hoặc 1 - Update)
        edMaTV.setEnabled(false);

        // Tạo Adapter cho Spinner giới tính
        ArrayAdapter<CharSequence> gioiTinhAdapter = ArrayAdapter.createFromResource(context, R.array.gioiTinhOptions, android.R.layout.simple_spinner_item);
        gioiTinhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGioiTinh.setAdapter(gioiTinhAdapter);

        if (type != 0) {
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());

            // Lấy giới tính từ cơ sở dữ liệu của đối tượng item (nếu có) và đặt giá trị cho Spinner
            String gioiTinh = item.getGioiTinh();
            if (gioiTinh != null) {
                int gioiTinhIndex = gioiTinhAdapter.getPosition(gioiTinh);
                spinnerGioiTinh.setSelection(gioiTinhIndex);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new ThanhVien();
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                item.setGioiTinh(spinnerGioiTinh.getSelectedItem().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        //type = 0 (insert)
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //type =1 (update)
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if (dao.updateTV(item) > 0) {
                            Toast.makeText(context, "Sứa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sứa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatTv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenTV.getText().length() == 0 || edNamSinh.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}