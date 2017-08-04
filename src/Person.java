
/**
 * @author chen
 *
 */
public class Person {
	private String name;
	private String phone;
	private String qq;
	private String address;

	public Person() {
		super();
	}

	public Person(String name, String phone, String qq, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.qq = qq;
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "\t" + phone + "\t" + qq + "\t" + address + "\n";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
