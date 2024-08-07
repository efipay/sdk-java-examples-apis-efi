package br.com.efi.pix.management.json;
import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixDevolution {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("e2eId", "E12345678202009091221abcdef12345");
        params.put("id", "1");

        JSONObject body = new JSONObject();
		    body.put("valor", "7.89");

        try {
            EfiPay efi= new EfiPay(options);
            JSONObject response = efi.call("pixDevolution", params, body);
            System.out.println(response);
          }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
          }
          catch (Exception e) {
            System.out.println(e.getMessage());
          }
    }
}
