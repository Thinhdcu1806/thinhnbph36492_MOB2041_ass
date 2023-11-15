package com.example.thinhnbph36492_mob2041_ass.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.thinhnbph36492_mob2041_ass.Adapter.TopAdapter;
import com.example.thinhnbph36492_mob2041_ass.DAO.PhieuMuonDAO;
import com.example.thinhnbph36492_mob2041_ass.Model.Top;
import com.example.thinhnbph36492_mob2041_ass.R;

import java.util.ArrayList;

public class TopFragment extends Fragment {
    ListView lvTop;
    ArrayList<Top> list;
    TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top10muon, container, false);
        lvTop = v.findViewById(R.id.lvTop);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
        list = (ArrayList<Top>) phieuMuonDAO.getTop();
        adapter = new TopAdapter(getActivity(), this, list);
        lvTop.setAdapter(adapter);
        
        return v;
    }
}