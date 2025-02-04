package br.com.efi.pix.webhooks.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixResendWebhook {

    public static void main(String[] args) {

        Credentials credentials = new Credentials();

        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        Map<String, Object> body = new HashMap<>();
        body.put("tipo", "PIX_RECEBIDO");

        List<String> e2eids = new ArrayList<>();
        e2eids.add("E09089356202412261300API229e5352");
        e2eids.add("E09089356202412261300API3149af57");
        body.put("e2eids", e2eids);

        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("pixResendWebhook", new HashMap<String, String>(), body);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
