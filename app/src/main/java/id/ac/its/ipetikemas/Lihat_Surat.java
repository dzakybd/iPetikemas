package id.ac.its.ipetikemas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Lihat_Surat extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nosurat)
    TextView nosurat;
    @BindView(R.id.tanggal_tempat)
    TextView tanggalTempat;
    @BindView(R.id.noitv)
    TextView noitv;
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.nocontainer)
    TextView nocontainer;
    @BindView(R.id.blok)
    TextView blok;
    @BindView(R.id.berakhir)
    TextView berakhir;
    @BindView(R.id.status)
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_surat);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nocontainer.setText(": "+getIntent().getStringExtra("nocontainer"));
        tanggalTempat.setText(getIntent().getStringExtra("tempat_tanggal"));
        nosurat.setText("Nomor Surat : "+getIntent().getStringExtra("nosurat"));
        blok.setText(": "+getIntent().getStringExtra("blok"));
        berakhir.setText(": "+getIntent().getStringExtra("tanggal"));
        noitv.setText(": "+getIntent().getStringExtra("noitv"));
        nama.setText(": "+getIntent().getStringExtra("nama"));
        Log.d("log",getIntent().getIntExtra("status",1)+"");
        switch (getIntent().getIntExtra("status",1)){
            case 3: status.setText("Selesai");
                status.setTextColor(Color.GREEN);
                break;
            case 0: status.setText("Batal");
                status.setTextColor(Color.RED);
                break;
            case -1: status.setText("Terlewat");
                status.setTextColor(Color.RED);
                break;
            case 2: status.setText("Diproses");
                status.setTextColor(Color.DKGRAY);
                break;
            default: status.setText("Menunggu");
                status.setTextColor(Color.LTGRAY);
                break;
        }
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
}
