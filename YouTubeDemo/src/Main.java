
public class Main {

	public static void main(String[] args) {
		/*
		 * CreditManager creditManager = new CreditManager(); creditManager.calculate();
		 * creditManager.calculate(); creditManager.save();
		 * 
		 * Customer customer = new Customer();//instance creation customer.id = 1;
		 * customer.city = "Ankara";
		 * 
		 * CustomerManager customerManager = new CustomerManager(customer);
		 * customerManager.save(); customerManager.delete();
		 * 
		 * Company company =new Company(); company.taxNumber = "100000";
		 * company.companyName = "Arçelik"; company.id =100;
		 * 
		 * Person person = new Person(); person.firstName = "Ahmet";
		 * person.nationalIdentity = "1234567890";
		 * 
		 * 
		 * CustomerManager customerManager2 = new CustomerManager(new Company());
		 * CustomerManager customerManager3 = new CustomerManager(new Person());
		 * CustomerManager customerManager4 = new CustomerManager(new Customer());
		 * 
		 * Customer c1 = new Customer(); Customer c2 = new Person(); Customer c3 = new
		 * Company();
		 */
		
		// IoC Container
		CustomerManager customerManager = new CustomerManager (new Customer (), new TeacherCreditManager());
		customerManager.giveCredit();
		
		CustomerManager customerManager2 = new CustomerManager (new Customer (), new CarCreditManager());
		customerManager2.giveCredit();
		
	}

}

class CreditManager {
	public void calculate() {
		System.out.println("Hesaplandı");
	}
	public void save() {
		System.out.println("Kaydedildi");
	}
}

interface ICreditManager {
	
	void calculate();
	
	void save();
	
}

abstract class BaseCreditManager implements ICreditManager {
	
	public abstract void calculate();
	
	public void save() {
		
		System.out.println("Kredi Kaydedildi");
	}
	
	
}

class TeacherCreditManager extends BaseCreditManager implements ICreditManager {

	@Override
	public void calculate() {
		System.out.println("Öğretmen Kredisi Hesaplandı.");
		
	}
}

class MilitaryCreditManager extends BaseCreditManager implements ICreditManager {

	@Override
	public void calculate() {
		System.out.println("Asker Kredisi Hesaplandı.");
		
	}
}

class CarCreditManager extends BaseCreditManager implements ICreditManager {

	@Override
	public void calculate() {
		System.out.println("Araba Kredisi Hesaplandı.");
		
	}
}

//SOLID

class Customer {
	
	public Customer(){
		System.out.println("Müşteri nesnesi başlatıldı");
	}
	
	int id;
	String city;
	
}

class Person extends Customer {
	
	String firstName;
	String lastName;
	String nationalIdentity;
}

class Company extends Customer {
	
	String companyName;
	String taxNumber;
	
}
// Katmanlı Mimariler

class CustomerManager {
	
	private Customer _customer;
	private ICreditManager _creditManager;
	
	public CustomerManager( Customer customer, ICreditManager creditManager) {
		
		_customer = customer;
		_creditManager = creditManager;
		
	}
	

	public void save() {
		
		System.out.println("Müşteri Kaydedildi: "+ _customer.id);
	}
	
	public void delete() {
		
		System.out.println("Müşteri Silindi: "+ _customer.id);
	}
	public void giveCredit() {
		_creditManager.calculate();
		System.out.println("Kredi verildi");
	}
}
