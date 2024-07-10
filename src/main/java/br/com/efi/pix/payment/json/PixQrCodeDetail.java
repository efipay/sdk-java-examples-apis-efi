package br.com.efi.pix.payment.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixQrCodeDetail {
    public static void main(String[] args) {

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		JSONObject body = new JSONObject();
		body.put("pixCopiaECola", "00020101021226830014BR.GOV.BCB.PIX2561qrcodespix.sejaefi.com.br/v2 41e0badf811a4ce6ad8a80b306821fce5204000053000065802BR5905EFISA6008SAOPAULO60070503***61040000");

			try {
				EfiPay efi= new EfiPay(options);
				JSONObject response = efi.call("pixQrCodeDetail", new HashMap<String,String>(), body);
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
