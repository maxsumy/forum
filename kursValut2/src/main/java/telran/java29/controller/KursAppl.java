package telran.java29.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.apache.commons.math3.util.Precision;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import telran.java29.dto.Kurs;

@RestController
@RequestMapping("/v1")
public class KursAppl {

	public static Kurs kurs;
	public static LocalDate dKurs;

	@GetMapping("/hello")

	public String hello(@RequestParam(name = "currencyFrom") String str1,
			@RequestParam(name = "currencyTo") String str2, @RequestParam String amount) throws URISyntaxException {
		if (!(LocalDate.now().equals(dKurs))) {
			RestTemplate restTemplate = new RestTemplate();
			RequestEntity<String> request = new RequestEntity<>(HttpMethod.GET,
					new URI("http://data.fixer.io/api/latest?access_key=81ebf276e1ed808b58591b5fb05c34eb"));

			ResponseEntity<Kurs> response = restTemplate.exchange(request, Kurs.class);

			kurs = response.getBody();
			dKurs = LocalDate.now();
		}
		double res = kurs.getRates().get(str2) / kurs.getRates().get(str1) * Double.parseDouble(amount);

		
		return amount + " " + str1 + " = " + Precision.round(res, 2) + " " + str2;
	}

}
