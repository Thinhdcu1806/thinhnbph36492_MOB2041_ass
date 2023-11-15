package com.example.thinhnbph36492_mob2041_ass.Fragment;

        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.app.SearchManager;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.widget.SearchView;
        import androidx.fragment.app.Fragment;

        import com.example.thinhnbph36492_mob2041_ass.Adapter.LoaiSachSpinnerAdapter;
        import com.example.thinhnbph36492_mob2041_ass.Adapter.SachAdapter;
        import com.example.thinhnbph36492_mob2041_ass.DAO.LoaiSachDAO;
        import com.example.thinhnbph36492_mob2041_ass.DAO.SachDAO;
        import com.example.thinhnbph36492_mob2041_ass.Model.LoaiSach;
        import com.example.thinhnbph36492_mob2041_ass.Model.Sach;
        import com.example.thinhnbph36492_mob2041_ass.R;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.List;

public class Frag_Sach extends Fragment {
    ListView lvSach;
    SachDAO sachDAO;
    SachAdapter adapter;
    Sach item;
    List<Sach> list;


    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue, edsoluong;
    Spinner spinner;
    Button btnSave, btnCancel;

    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;

    private SearchView searchView;


    public Frag_Sach() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.drawer_view1, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handleSearch(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_qlsach, container, false);
        lvSach = v.findViewById(R.id.lvSach);

        sachDAO = new SachDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fltAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<Sach>) sachDAO.getAll();
        adapter = new SachAdapter(getActivity(), this, list);
        lvSach.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDAO.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Không xóa", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_sach);
        edMaSach = dialog.findViewById(R.id.edtMaSach_itemSach);
        edTenSach = dialog.findViewById(R.id.edtTenSach_itemSach);
        edGiaThue = dialog.findViewById(R.id.edtGiaThue_itemSach);
        edsoluong = dialog.findViewById(R.id.edtSoLuong_itemSach);
        spinner = dialog.findViewById(R.id.spLoaiSach);

        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);

        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

        spinnerAdapter = new LoaiSachSpinnerAdapter(context, listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        // lay maLoaiSach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // kiem tra tupe insert hay update
        edMaSach.setEnabled(false);
        if (type != 0) {
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            edsoluong.setText(String.valueOf(item.getSoluong()));

            for (int i = 0; i < listLoaiSach.size(); i++)
                if (item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);
            spinner.setSelection(position);
        }




        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();
                item.setTenSach(edTenSach.getText().toString());
                item.setGiaThue(parseInt(edGiaThue.getText().toString(), 0));
                item.setSoluong(parseInt(edGiaThue.getText().toString() ,0));
                item.setMaLoai(maLoaiSach);

                if (validate() > 0) {
                    if (type == 0) {
                        if (sachDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (sachDAO.updateS(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sứa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenSach.getText().length() == 0 || edGiaThue.getText().length() == 0 || edsoluong.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    private void handleSearch(String query) {
        List<Sach> listSearch = new ArrayList<>();
        for (Sach sach : list) {
            if (sach.getTenSach().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(sach);
            }
        }
        adapter = new SachAdapter(getActivity(), this, listSearch);
        lvSach.setAdapter(adapter);

    }

}
