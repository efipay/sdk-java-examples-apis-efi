package br.com.efi.pix.payment.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixQrCodePay {
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
		
		Map<String, Object> pagador = new HashMap<String, Object>();
        pagador.put("chave", "Insira_aqui_sua_chave");
        body.put("pagador", pagador);

        body.put("pixCopiaECola", "00020101021226830014BR.GOV.BCB.PIX2561qrcodespix.sejaefi.com.br/v2 41e0badf811a4ce6ad8a80b306821fce5204000053000065802BR5905EFISA6008SAOPAULO60070503***61040000");
			
		try {
				EfiPay efi= new EfiPay(options);
				
				Map<String, Object> response = efi.call("pixQrCodePay", params, body);
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
