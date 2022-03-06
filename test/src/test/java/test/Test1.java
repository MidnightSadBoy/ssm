package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test1 {

	public static void test() {
		String b = null;
		System.out.println("--------------");
		System.out.println(b);
//		tt();
	}

	public void tt() {
		System.out.println("this is tt!");
	}

	public static class ok {
		int i = 0;

	}

	public static void main(String[] args) {
		ok test1 = new Test1().ok();
		test1.i = 2;
		System.out.println("ok!");
	}
}
