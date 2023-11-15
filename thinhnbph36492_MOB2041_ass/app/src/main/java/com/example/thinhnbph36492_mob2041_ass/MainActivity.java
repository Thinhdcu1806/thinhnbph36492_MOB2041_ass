package com.example.thinhnbph36492_mob2041_ass;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.thinhnbph36492_mob2041_ass.Fragment.AddUserFragment;
import com.example.thinhnbph36492_mob2041_ass.Fragment.DoanhThuFragment;
import com.example.thinhnbph36492_mob2041_ass.Fragment.Frag_DoiPass;
import com.example.thinhnbph36492_mob2041_ass.Fragment.Frag_QLThanhVien;
import com.example.thinhnbph36492_mob2041_ass.Fragment.Frag_Sach;
import com.example.thinhnbph36492_mob2041_ass.Fragment.LoaiSachFragment;
import com.example.thinhnbph36492_mob2041_ass.Fragment.PhieuMuonFragment;
import com.example.thinhnbph36492_mob2041_ass.Fragment.TopFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.navigationDrawerA);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);// gán toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setItemIconTintList(null);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_PhieuMuon) {
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    replaceFrg(phieuMuonFragment);
                } else if (item.getItemId() == R.id.nav_LoaiSach) {
                     LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                    replaceFrg(loaiSachFragment);
                } else if (item.getItemId() == R.id.nav_Sach) {
                    Frag_Sach sachFragment = new Frag_Sach();
                    replaceFrg(sachFragment);
                } else if (item.getItemId() == R.id.nav_TopMuon) {
                    TopFragment top10Fragment = new TopFragment();
                    replaceFrg(top10Fragment);
                } else if (item.getItemId() == R.id.nav_DoanhThu) {
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    replaceFrg(doanhThuFragment);
                } else if (item.getItemId() == R.id.nav_ThemThanhVien) {
                    AddUserFragment addUserFragment = new AddUserFragment();
                    replaceFrg(addUserFragment);
                } else if (item.getItemId() == R.id.nav_DoiMatKhau) {
                    Frag_DoiPass changePassFragment = new Frag_DoiPass();
                    replaceFrg(changePassFragment);
                } else if (item.getItemId() == R.id.nav_ThanhVien) {
                    Frag_QLThanhVien thanhVienFragment = new Frag_QLThanhVien();
                    replaceFrg(thanhVienFragment);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Cảnh báo");
                    builder.setIcon(R.drawable.baseline_warning_24);
                    builder.setMessage("Bạn có muốn đăng xuất không?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                getSupportActionBar().setTitle(item.getTitle()); //khi click vào item hiển thị tieu de lên toolbar
                drawerLayout.close(); //đóng nav
                return true;
            }
        });
    }

    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frag_content, frg).commit();
    }
}