package io.yesez.android_dev_ni_sample;

/**
 * Created by Jimmy on 05-16-14.
 */
public class User {

	public static String ID_FIELD = "id";
	public static String NAME_FIELD = "name";
	public static String EMAIL_FIELD = "email";
	public static String PHONE_FIELD = "phone";
	public static String WEBSITE_FIELD = "website";

	private int id;
	private String name;
	private String email;
	private String phone;
	private String website;
	private Address address;

	public User(){}
	public User(int id, String name, String email, String phone, String website, Address address){
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.address = address;
	}

	@Override
	public String toString(){
		return String.format("id: %d name: %s email: %s phone: %s web: %s", id, name, email, phone, website);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public class Address{
		private String street;
		private String city;
		private String zipcode;

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
	}
}
