package javaeetutorial.producermethods;

import java.util.function.Supplier;

public enum CoderType {
	TEST(TestCoderImpl::new), ACTUAL(CoderImpl::new);
	
	private final Supplier<Coder> creater;
	private CoderType(Supplier<Coder> creater) {
		this.creater  =creater;
	}

	public Coder create() {
		return creater.get();
	}
}
