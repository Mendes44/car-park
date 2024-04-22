package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

//Classe de Serviço de aluguel
public class RentalService {

	private Double pricePerHours;
	private Double pricePerDays;
	
	//Aqui estou falando que minha classe RentalService depende da minha interface onde me possibilita uma melhor manutenção.
	private TaxService TaxService;
	
	public RentalService(Double pricePerHours, Double pricePerDays, TaxService Taxservice) {

		this.pricePerHours = pricePerHours;
		this.pricePerDays = pricePerDays;
		this.TaxService = Taxservice;
	}
	
	//Função para calcular o valor do aluguel. Onde se passar de 12 horas será cobrado por dia.
	public void processInvoice (CarRental carRental) {
		
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minutes / 60.0;
		
		double basicPayment;
		if (hours <= 12.0) {
			basicPayment = pricePerHours * Math.ceil(hours);
		}
		else {
			basicPayment = pricePerDays * Math.ceil(hours/24.0);
		}
		
		double tax = TaxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment,tax));
		/*Utilizei o Math.ceil para arredondar o valor da hora para cima, 
		 *assim consigo fazer o calculo com mais precissão. 
		*/
	}
}