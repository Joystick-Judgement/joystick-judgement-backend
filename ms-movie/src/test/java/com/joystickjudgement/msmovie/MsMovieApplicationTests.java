package com.joystickjudgement.msmovie;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class MsMovieApplicationTests {

	@Test
	void basicTest()
	{
		Assertions.assertThat(1+1).isEqualTo(2);
	}

}
