package ai.bisa.webinarbatch3.function;

import android.text.TextUtils;
import android.util.Patterns;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Script_Function {
    public String path = "https://tokoelektronikokta.000webhostapp.com/images/barang/";
    public String path_photoUser = "https://tokoelektronikokta.000webhostapp.com/images/photo_profile_user/";
    public String pathBanner = "https://tokoelektronikokta.000webhostapp.com/images/slide/";
    public String path_detailImg = "https://tokoelektronikokta.000webhostapp.com/images/barang/detail/";
    Locale localeID = new Locale("in", "ID");
    public NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    public boolean isValidateEmail(String email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    Calendar c1 = Calendar.getInstance();
    SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yyy h:m:s a");
    public String strdate = sdf1.format(c1.getTime());
}
