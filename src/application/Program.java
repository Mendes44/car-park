package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy HH:MM): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Retorno (dd/MM/yyyy HH:MM): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		//Objeto para capturar a data informada pelo usuario
		CarRental cr = new CarRental (start, finish, new Vehicle(carModel));
		
		System.out.print("Entre com o preço da Hora: ");
		double pricePerHours = sc.nextDouble();
		System.out.print("Entre com o preço por Dia: ");
		double pricePerDay= sc.nextDouble();
		
		//Instancio o objeto RentalService para capturar o preço da hora e dia. E chamo o processInvoice para capturar os dados do veiculo
		RentalService rentalService = new RentalService(pricePerHours, pricePerDay, new BrazilTaxService());
		rentalService.processInvoice(cr);
		
		System.out.println("FATURA:");
		System.out.println("Pagamento Básico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento Total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		sc.close();

	}

}
