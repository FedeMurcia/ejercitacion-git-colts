package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.*;

import org.jaga.util.BitString;
import org.junit.Before;
import org.junit.Test;

public class BitStringTest {

	private BitString bitString;

	@Before
	public void setUp() throws Exception {
		bitString = new BitString(4);
	}

	@Test
	public void test() {
//		bitString.set(1, true);
//		bitString.set(2, true);
//		bitString.set(3, true);
//		bitString.set(0, true);
//		System.out.println(bitString);
		System.out.println(((0x01) | 'A')== 'A');
		System.out.println(((0x02) | 'A')== 'A');
		System.out.println(((0x04) | 'A')== 'A');
		System.out.println(((0x08) | 'A')== 'A');
		System.out.println(((0x10) | 'A')== 'A');
		System.out.println(((0x20) | 'A')== 'A');
		System.out.println(((0x40) | 'A')== 'A');
		System.out.println(((0x80) | 'A')== 'A');
		
		
	}

}
