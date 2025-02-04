package br.com.efi.statements.management.map;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class CreateStatementRecurrency {

    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("periodicidade", "diario");
        body.put("envia_email", true);
        body.put("comprimir_arquivos", true);

        try {
            EfiPay efi = new EfiPay(options);

            Map<String, Object> response = efi.call("createStatementRecurrency", new HashMap<String, String>(), body);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
