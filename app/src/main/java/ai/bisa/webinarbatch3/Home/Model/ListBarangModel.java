package ai.bisa.webinarbatch3.Home.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListBarangModel {
    @SerializedName("barang")
    private List<BarangModel> listBarangM;
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public ListBarangModel(List<BarangModel> listBarangM, int status, String message) {
        this.listBarangM = listBarangM;
        this.status = status;
        this.message = message;
    }

    public List<BarangModel> getListBarangM() {
        return listBarangM;
    }

    public void setListBarangM(List<BarangModel> listBarangM) {
        this.listBarangM = listBarangM;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
