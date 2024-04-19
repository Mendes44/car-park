package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

//Classe de Serviço de aluguel
public class RentalService {

	private Double pricePerHours;
	private Double pricePerDays;
	
	private BrazilTaxService brTaxService;
	
	public RentalService(Double pricePerHours, Double pricePerDays, BrazilTaxService brTaxservice) {

		this.pricePerHours = pricePerHours;
		this.pricePerDays = pricePerDays;
		this.brTaxService = brTaxservice;
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
		
		double tax = brTaxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment,tax));
		/*Utilizei o Math.ceil para arredondar o valor da hora para cima, 
		 *assim consigo fazer o calculo com mais precissão. 
		*/
	}
}