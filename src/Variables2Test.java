import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Variables2Test {

	@Test
	public void test() {
		Variables2 uut = new Variables2();
		Object[] ret = uut.run();
		double m1 = (double) ret[0];
		int m2 = (int) ret[1];
		double res = (double) ret[2];
		boolean eq = (boolean) ret[3];
		boolean check = Math.abs(m1 * m2 - res) < 0.001;
		assertEquals("<mit den Parametern m1 = " + m1 + ", m2 = " + m2 + ", res = " + res + ">", check, eq);
	}

}
