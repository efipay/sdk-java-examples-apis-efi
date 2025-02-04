package br.com.efi.pix.webhooks.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixResendWebhook {

    public static void main(String[] args) {

        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        JSONObject body = new JSONObject();
        body.put("tipo", "PIX_RECEBIDO");

        JSONArray e2eids = new JSONArray();
        e2eids.put("E09089356202412261300API229e5352");
        e2eids.put("E09089356202412261300API3149af57");

        body.put("e2eids", e2eids);

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixResendWebhook", new HashMap<String, String>(), body);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
