import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MethodsTest {

	@Test
	public void test1() {
		Methods uut = new Methods();
		int in = 0;
		assertEquals("<addUp(" + in + ")>", in + 1, uut.addUp(in));
	}

	@Test
	public void test2() {
		Methods uut = new Methods();
		int in = 2;
		assertEquals("<addUp(" + in + ")>", in + 1, uut.addUp(in));
	}

	@Test
	public void test3() {
		Methods uut = new Methods();
		int in = -12;
		assertEquals("<addUp(" + in + ")>", in - 1, uut.addUp(in));
	}

	@Test
	public void test4() {
		Methods uut = new Methods();
		int in = -1;
		assertEquals("<addUp(" + in + ")>", in - 1, uut.addUp(in));
	}

	@Test
	public void testPrivate() {
		Methods uut = new Methods();
		int in = 19;
		assertEquals("private", in + 1, uut.addUp(in));
		in = 623478;
		assertEquals("private", in + 1, uut.addUp(in));
		in = -123456;
		assertEquals("private", in - 1, uut.addUp(in));
	}
}
