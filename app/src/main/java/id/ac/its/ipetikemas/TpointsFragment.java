package id.ac.its.ipetikemas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class TpointsFragment extends Fragment {


    @BindView(R.id.points)
    TextView points;
    @BindView(R.id.listpoin)
    RecyclerView listpoin;
    Unbinder unbinder;
    List<Poin_Item> poin_items;
    Poin_Item poin_item;

    public TpointsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tpoints, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeData();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        listpoin.setLayoutManager(llm);
        listpoin.setHasFixedSize(true);
        PoinRecyclerAdapter adapter = new PoinRecyclerAdapter(getActivity(), poin_items);
        listpoin.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializeData() {
        poin_items = new ArrayList<>();
        int total=0;
        poin_item = new Poin_Item();
        poin_item.nocontainer="A1";
        poin_item.pengiriman=25;
        poin_item.waktu=25;
        poin_item.kecepatan=25;
        poin_items.add(poin_item);
        total+=poin_item.pengiriman+poin_item.waktu+poin_item.kecepatan;

        poin_item = new Poin_Item();
        poin_item.nocontainer="B1";
        poin_item.pengiriman=26;
        poin_item.waktu=23;
        poin_item.kecepatan=29;
        poin_items.add(poin_item);
        total+=poin_item.pengiriman+poin_item.waktu+poin_item.kecepatan;

        poin_item = new Poin_Item();
        poin_item.nocontainer="C1";
        poin_item.pengiriman=31;
        poin_item.waktu=32;
        poin_item.kecepatan=24;
        poin_items.add(poin_item);
        total+=poin_item.pengiriman+poin_item.waktu+poin_item.kecepatan;

        poin_item = new Poin_Item();
        poin_item.nocontainer="D1";
        poin_item.pengiriman=24;
        poin_item.waktu=28;
        poin_item.kecepatan=33;
        poin_items.add(poin_item);
        total+=poin_item.pengiriman+poin_item.waktu+poin_item.kecepatan;

        poin_item = new Poin_Item();
        poin_item.nocontainer="E1";
        poin_item.pengiriman=23;
        poin_item.waktu=30;
        poin_item.kecepatan=25;
        poin_items.add(poin_item);
        total+=poin_item.pengiriman+poin_item.waktu+poin_item.kecepatan;

        points.setText(String.valueOf(total));

    }
}
