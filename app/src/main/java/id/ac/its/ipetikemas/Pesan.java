package id.ac.its.ipetikemas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Pesan extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listpesan)
    RecyclerView listpesan;
    List<Pesan_Item> pesan_itemList;
    Pesan_Item pesan_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeData();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listpesan.setLayoutManager(llm);
        listpesan.setHasFixedSize(true);
        PesanRecyclerAdapter adapter = new PesanRecyclerAdapter(this, pesan_itemList);
        listpesan.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeData() {
        pesan_itemList = new ArrayList<>();

        pesan_item = new Pesan_Item();
        pesan_item.foto=R.drawable.p1;
        pesan_item.konten="Segera ambil muatan!";
        pesan_item.nama="Budi (Admin)";
        pesan_item.waktu="22/04/2017 08.00";
        pesan_itemList.add(pesan_item);

        pesan_item = new Pesan_Item();
        pesan_item.foto=R.drawable.p2;
        pesan_item.konten="Andi posisi dimana?";
        pesan_item.nama="Budi (ITV)";
        pesan_item.waktu="22/04/2017 07.00";
        pesan_itemList.add(pesan_item);

        pesan_item = new Pesan_Item();
        pesan_item.foto=R.drawable.p3;
        pesan_item.konten="apa kabar kawan";
        pesan_item.nama="Candra (ITV)";
        pesan_item.waktu="22/04/2017 06.00";
        pesan_itemList.add(pesan_item);
    }
}
