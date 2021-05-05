package Util;

public class BASE64 {
    public String encode(String pass){
        String encode_pass=android.util.Base64.encodeToString(pass.getBytes(), android.util.Base64.DEFAULT);
        return encode_pass;
    }

    public String decode(String pass){
        String decode_pass=android.util.Base64.decode(pass, android.util.Base64.DEFAULT).toString();
        return decode_pass;
    }
}
