package br.com.efi.pix.batch.cobv.json;

import java.util.HashMap;

import org.json.JSONObject;
import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class pixListDueChargeBatch {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

    	HashMap<String, String> params = new HashMap<String, String>();
		params.put("inicio", "2024-01-01T00:00:00Z");
		params.put("fim", "2024-12-31T23:59:59Z");

		try {
			EfiPay efi= new EfiPay(options);
			JSONObject response = efi.call("pixListDueChargeBatch", params, new JSONObject());
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
