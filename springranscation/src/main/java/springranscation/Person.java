package springranscation;

public class Person {
	private String name;
	private String sex;
	public Person(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
		System.out.println("调用了构造方法");
	}
	public Person() {
		super();
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + "]";
	}
	
}
