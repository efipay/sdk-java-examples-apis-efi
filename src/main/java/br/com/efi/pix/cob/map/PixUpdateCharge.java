package br.com.efi.pix.cob.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixUpdateCharge {
    public static void main(String[] args) throws IOException {
      Credentials credentials = new Credentials();

      HashMap<String, Object> options = new HashMap<String, Object>();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("valor", new HashMap<String, Object>().put("original", "5.00"));

        try {
          EfiPay efi = new EfiPay(options);
          Map<String, Object> response = efi.call("pixUpdateCharge", params, body);
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