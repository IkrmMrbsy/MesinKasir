package org.example.Service;

import org.example.Database.DAO.TransaksiDao;
import org.example.Database.DAO.TransaksiDetailDao;
import org.example.Model.Transaksi;
import org.example.Model.TransaksiDetail;

import java.util.List;

public class TransaksiService {
    private TransaksiDao transaksiDao = new TransaksiDao();
    private TransaksiDetailDao transaksiDetailDao = new TransaksiDetailDao();

    public int prosesTransaksi(Transaksi transaksi, TransaksiDetail transaksiDetail) {
        int transaksiID = transaksiDao.insertTransaksi(transaksi);

        if (transaksiID != -1) {
            // Set transaksiID pada TransaksiDetail
            transaksiDetail.setTransaksiID(transaksiID);
            transaksiDetailDao.insertTransaksiDetail(transaksiDetail);
        }

        return transaksiID;
    }

    private double hitungTotal(List<TransaksiDetail> detailProduk){
        double total = 0;
        for (TransaksiDetail detail : detailProduk){
            total += detail.getHarga() * detail.getJumlah();
        }
        return total;
    }
}
