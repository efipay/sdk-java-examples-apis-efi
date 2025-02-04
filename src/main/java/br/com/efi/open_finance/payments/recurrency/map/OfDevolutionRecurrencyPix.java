package br.com.efi.open_finance.payments.recurrency.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfDevolutionRecurrencyPix {

    public static void main(String[] args) {
        Credentials credentials = new Credentials();

        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("identificadorPagamento", "urn:efi:ae71713f-875b-4af3-9d85-0bcb43288847");

        List<Map<String, Object>> bodyList = new ArrayList<>();

        Map<String, Object> devolucao1 = new HashMap<>();
        devolucao1.put("valor", "0.01");
        devolucao1.put("endToEndId", "E09089356202408281500624f423208f");

        Map<String, Object> devolucao2 = new HashMap<>();
        devolucao2.put("valor", "0.01");
        devolucao2.put("endToEndId", "E09089356202408291500a0ecaa22e86");

        bodyList.add(devolucao1);
        bodyList.add(devolucao2);

        JSONArray body = new JSONArray(bodyList);

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
