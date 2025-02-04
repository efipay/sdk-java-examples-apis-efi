package br.com.efi.statements.management.json;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateStatementRecurrency {

    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("identificador", "semanal");

        JSONObject body = new JSONObject();
        body.put("periodicidade", "diario");
        body.put("status", "ativo");
        body.put("envia_email", true);
        body.put("comprimir_arquivos", true);

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("updateStatementRecurrency", params, body);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
