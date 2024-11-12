package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaksiDetail {
    private int transaksiDetailID;
    private int transaksiID;
    private int produkID;
    private int jumlah;
    private double harga;
}
