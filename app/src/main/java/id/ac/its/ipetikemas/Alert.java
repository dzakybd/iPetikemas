package id.ac.its.ipetikemas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Alert extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.darurat)
    CardView darurat;
    @BindView(R.id.rusak)
    CardView rusak;
    @BindView(R.id.listalert)
    RecyclerView listalert;
    List<Pesan_Item> pesan_itemList;
    Pesan_Item pesan_item;
    @BindView(R.id.view)
    LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeData();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listalert.setLayoutManager(llm);
        listalert.setHasFixedSize(true);
        PesanRecyclerAdapter adapter = new PesanRecyclerAdapter(this, pesan_itemList);
        listalert.setAdapter(adapter);
    }

    @OnClick({R.id.darurat, R.id.rusak})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.darurat:
                alert("Darurat");
                break;
            case R.id.rusak:
                alert("Kerusakan");
                break;
        }
    }

    private void alert(final String alert){
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        pilihan.setMessage("Anda ingin mengirim alert?");
        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(view, "Alert "+alert+" terkirim", Snackbar.LENGTH_LONG).show();
            }
        });
        pilihan.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog mes = pilihan.create();
        mes.show();
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
        pesan_item.foto = R.drawable.p1;
        pesan_item.konten = "Segera ambil muatan!";
        pesan_item.nama = "Budi (Admin)";
        pesan_item.waktu = "22/04/2017 08.00";
        pesan_itemList.add(pesan_item);

        pesan_item = new Pesan_Item();
        pesan_item.foto = R.drawable.p1;
        pesan_item.konten = "Kembali ke terminal!";
        pesan_item.nama = "Budi (Admin)";
        pesan_item.waktu = "22/04/2017 07.00";
        pesan_itemList.add(pesan_item);
    }
}
