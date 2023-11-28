package com.joystickjudgement.msgame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class MsGameApplicationTests {

	@Test
	void basicTest()
	{
		Assertions.assertThat(1+1).isEqualTo(2);
	}

}
