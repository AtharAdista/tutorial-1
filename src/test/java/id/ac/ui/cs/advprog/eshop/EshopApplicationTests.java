package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EshopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMain(){
		EshopApplication.main(new String[] {});
	}

}
