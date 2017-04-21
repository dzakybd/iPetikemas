package id.ac.its.ipetikemas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilham Aulia Majid on 19-Apr-17.
 */

public class SuratRecyclerAdapter extends RecyclerView.Adapter<SuratRecyclerAdapter.ViewHolder> {

    Context context;
    List<Surat_Item> surat_items;


    public SuratRecyclerAdapter(Context context, List<Surat_Item> surat_items) {
        this.context = context;
        this.surat_items = surat_items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Surat_Item surat_item = surat_items.get(position);
        holder.nocontainer.setText(surat_item.nocontainer);
        holder.nosurat.setText("Surat No. " + surat_item.nomor);
        holder.blok.setText("Blok " + surat_item.blok);
        holder.tanggal.setText("Berakhir " + surat_item.tanggal_end);
        switch (surat_item.status) {
            case 3:
                holder.status.setText("Selesai");
                holder.status.setTextColor(Color.GREEN);
                break;
            case 0:
                holder.status.setText("Batal");
                holder.status.setTextColor(Color.RED);
                break;
            case -1:
                holder.status.setText("Terlewat");
                holder.status.setTextColor(Color.RED);
                break;
            case 2:
                holder.status.setText("Diproses");
                holder.status.setTextColor(Color.DKGRAY);
                break;
            default:
                holder.status.setText("Menunggu");
                holder.status.setTextColor(Color.LTGRAY);
                break;
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("log", surat_item.status + "");
                Intent intent = new Intent(context, Lihat_Surat.class);
                intent.putExtra("nocontainer", surat_item.nocontainer);
                intent.putExtra("nosurat", surat_item.nomor);
                intent.putExtra("blok", surat_item.blok);
                intent.putExtra("tanggal", surat_item.tanggal_end);
                intent.putExtra("status", surat_item.status);
                intent.putExtra("noitv", surat_item.noitv);
                intent.putExtra("nama", surat_item.nama);
                intent.putExtra("tempat_tanggal", surat_item.tempat_tanggal);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return surat_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nocontainer)
        TextView nocontainer;
        @BindView(R.id.nosurat)
        TextView nosurat;
        @BindView(R.id.blok)
        TextView blok;
        @BindView(R.id.tanggal)
        TextView tanggal;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.view)
        CardView view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
