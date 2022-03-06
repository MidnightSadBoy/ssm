package test;

public class Person {

	private int age;

	private Person Instance = new Person(5);

	Person() {

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private Person(int age) {
		this.age = age;
	}

	public void say() {
		System.out.println("yes!-----------------" + age);
	}

	public Person gteInstance() {
		return Instance;
	}

	public static void main(String[] args) {
		Test1.test();
	}
}
