package Util;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

import Util.*;

public class ConvertImage {
    public String getStringFromBitmap(Bitmap bitmapPicture) {
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = new BASE64().encode(b.toString());
        return encodedImage;
    }
}
