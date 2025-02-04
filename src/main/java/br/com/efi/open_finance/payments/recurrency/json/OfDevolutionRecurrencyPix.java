package br.com.efi.open_finance.payments.recurrency.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfDevolutionRecurrencyPix {

    public static void main(String[] args) {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<>();
        params.put("identificadorPagamento", "urn:efi:ae71713f-875b-4af3-9d85-0bcb43288847");

        JSONArray body = new JSONArray();

        JSONObject devolucao1 = new JSONObject();
        devolucao1.put("valor", "0.01");
        devolucao1.put("endToEndId", "E09089356202408281500624f423208f");

        JSONObject devolucao2 = new JSONObject();
        devolucao2.put("valor", "0.01");
        devolucao2.put("endToEndId", "E09089356202408291500a0ecaa22e86");

        body.put(devolucao1);
        body.put(devolucao2);

        try {
            EfiPay efi = new EfiPay(options);
            JSONArray response = efi.callArray("ofDevolutionRecurrencyPix", params, body);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}