package br.com.efi.pix.location.map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;

public class PixGenerateQRCode {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

    HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("id", "0");

        try {
          EfiPay efi= new EfiPay(options);
          Map<String, Object> response = efi.call("pixGenerateQRCode", params, new HashMap<String, Object>());
          
          ImageIO.write(ImageIO.read(new ByteArrayInputStream(javax.xml.bind.DatatypeConverter.parseBase64Binary(((String) response.get("imagemQrcode")).split(",")[1]))), "png", new File("./imagensQrCode/image_" + new SimpleDateFormat("dd-MM_HHmmss").format(new Date()) + ".png"));
           
        }catch (EfiPayException e){
          System.out.println(e.getError());
          System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
          System.out.println(e.getMessage());
        }
	  }
}
