package be.ehb.eindproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@org.springframework.test.context.TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
		"spring.datasource.username=sa",
		"spring.datasource.password="
})
class EindprojectApplicationTests {

	@Test
	void contextLoads() {
	}

}
