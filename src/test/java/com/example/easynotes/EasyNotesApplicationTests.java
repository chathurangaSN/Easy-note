package com.example.easynotes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class EasyNotesApplicationTests {

	Calculator calculator = new Calculator();

	@Test
	void itShouldAddNumbers() {
		//given
		int numOne =20;
		int numTwo = 30;

		//when
		int result = calculator.add(numOne,numTwo);

		//then
		assertThat(result).isEqualTo(50);

	}

	class Calculator{
		int add(int a, int b){
			return a+b;
		}
	}

}
