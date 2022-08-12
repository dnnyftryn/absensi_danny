package id.humaedi.absensi.ui.absensi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.humaedi.absensi.api.testdummy.data.DataAbsensi
import id.humaedi.absensi.databinding.ItemDaftarAbsensiBinding

class AbsensiAdapter: RecyclerView.Adapter<AbsensiAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(data: DataAbsensi)
    }
    var listAbsensi = arrayListOf<DataAbsensi>()

        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            listAbsensi.clear()
            listAbsensi.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemDaftarAbsensiBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listAbsensi[position])
    }

    override fun getItemCount(): Int = listAbsensi.size

    inner class ListViewHolder(private val binding: ItemDaftarAbsensiBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataAbsensi) {
            with(binding) {
                binding.tvJam.text = data.jam
                binding.tvKeterangan.text = data.keterangan
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(data) }
            }
        }
    }
}