package id.ac.its.ipetikemas;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.os.Looper.getMainLooper;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuratFragment extends Fragment {

    List<Surat_Item> surat_items;
    Surat_Item surat_item;
    @BindView(R.id.listsurat)
    RecyclerView listsurat;
    Unbinder unbinder;
    @BindView(R.id.tanggal)
    TextView tanggal;
    @BindView(R.id.waktu)
    TextView waktu;

    public SuratFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_surat, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeData();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        listsurat.setLayoutManager(llm);
        listsurat.setHasFixedSize(true);
        SuratRecyclerAdapter adapter = new SuratRecyclerAdapter(getActivity(), surat_items);
        listsurat.setAdapter(adapter);
        tanggal.setText("Sabtu, 22 April 2017");
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                waktu.setText(new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializeData() {
        surat_items = new ArrayList<>();

        surat_item = new Surat_Item();
        surat_item.status = 2;
        surat_item.nama = "Andi";
        surat_item.noitv = "L 123 HA";
        surat_item.blok = "A";
        surat_item.nocontainer = "A1";
        surat_item.nomor = "1/ABC";
        surat_item.tanggal_end = "22 April 2017 - 18.00";
        surat_item.tempat_tanggal = "Surabaya, 22 April 2017";
        surat_items.add(surat_item);

        surat_item = new Surat_Item();
        surat_item.status = 1;
        surat_item.nama = "Andi";
        surat_item.noitv = "L 123 HA";
        surat_item.blok = "B";
        surat_item.nocontainer = "B1";
        surat_item.nomor = "2/ABC";
        surat_item.tanggal_end = "22 April 2017 - 19.00";
        surat_item.tempat_tanggal = "Surabaya, 22 April 2017";
        surat_items.add(surat_item);

        surat_item = new Surat_Item();
        surat_item.status = 3;
        surat_item.nama = "Andi";
        surat_item.noitv = "L 123 HA";
        surat_item.blok = "C";
        surat_item.nocontainer = "C1";
        surat_item.nomor = "3/ABC";
        surat_item.tanggal_end = "22 April 2017 - 07.00";
        surat_item.tempat_tanggal = "Surabaya, 22 April 2017";
        surat_items.add(surat_item);

        surat_item = new Surat_Item();
        surat_item.status = 0;
        surat_item.nama = "Andi";
        surat_item.noitv = "L 123 HA";
        surat_item.blok = "D";
        surat_item.nocontainer = "D1";
        surat_item.nomor = "4/ABC";
        surat_item.tanggal_end = "22 April 2017 - 08.00";
        surat_item.tempat_tanggal = "Surabaya, 22 April 2017";
        surat_items.add(surat_item);

        surat_item = new Surat_Item();
        surat_item.status = -1;
        surat_item.nama = "Andi";
        surat_item.noitv = "L 123 HA";
        surat_item.blok = "E";
        surat_item.nocontainer = "E1";
        surat_item.nomor = "5/ABC";
        surat_item.tanggal_end = "22 April 2017 - 06.00";
        surat_item.tempat_tanggal = "Surabaya, 22 April 2017";
        surat_items.add(surat_item);
    }
}
