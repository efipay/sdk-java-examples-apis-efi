package br.com.efi.pix.location.json;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixCreateLocation {

    public static void main(String[] args) throws IOException {

        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        JSONObject body = new JSONObject();
        body.put("tipoCob", "cob");

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateLocation", new HashMap<String, String>(), body);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
