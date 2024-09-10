package br.com.efi.exclusives.account.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateAccountConfig {
    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		Map<String, Object> body = new HashMap<String, Object>();	
		
		Map<String, Object> qrCodeEstatico = new HashMap<String, Object>();
        qrCodeEstatico.put("recusarTodos", false);

        Map<String, Object> recebimento = new HashMap<String, Object>();
        recebimento.put("txidObrigatorio", true);
        recebimento.put("qrCodeEstatico", qrCodeEstatico);

        Map<String, Object> chave = new HashMap<String, Object>();
        chave.put("recebimento", recebimento);

        Map<String, Object> chaves = new HashMap<String, Object>();
        chaves.put("Insira_aqui_sua_chave", chave);

        Map<String, Object> pix = new HashMap<String, Object>();
        pix.put("receberSemChave", true);
        pix.put("chaves", chaves);

        body.put("pix", pix);

			try {
				EfiPay efi = new EfiPay(options);
				Map<String, Object> response = efi.call("updateAccountConfig", new HashMap<String,String>(), body);
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