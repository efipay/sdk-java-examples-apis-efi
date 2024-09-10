package br.com.efi.pix.payment.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixSend {
    public static void main(String[] args) {

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("idEnvio", "12453567890123456789");

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("valor", "99.99");

		Map<String, Object> pagador = new HashMap<String, Object>();
        pagador.put("chave", "Insira_aqui_sua_chave");
        body.put("pagador", pagador);
		
		Map<String, Object> favorecido = new HashMap<String, Object>();
        favorecido.put("chave", "joão@meuemail.com");
        body.put("favorecido", favorecido);

			try {
				EfiPay efi= new EfiPay(options);
				
				Map<String, Object> response = efi.call("pixSend", params, body);
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
