package com.design.pattern.creational;

// It is a Creational Pattern
// Used when we want to build an object made up from other objects
// When we want to keep the creation of other object separate from main object
public class BuilderPattern {
	public static void main(String args[]) {
		Url url = Url.Builder.newInstance()
				.setProtocal("http://")
				.setHosthostname("www.api.google.com")
				.build();

		System.out.println(url);
	}
}

final class Url {

	// final instance fields
	private final String protocal;
	private final String hostname;

	public Url(Builder builder) {
		this.protocal = builder.protocal;
		this.hostname = builder.hostname;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private String protocal;
		private String hostname;

		public static Builder newInstance() {
			return new Builder();
		}

		private Builder() {
		}

		public Builder setProtocal(String protocal) {
			this.protocal = protocal;
			return this;
		}

		public Builder setHosthostname(String hostname) {
			this.hostname = hostname;
			return this;
		}

		// build method to deal with outer class to return outer instance
		public Url build() {
			return new Url(this);
		}
	}

	@Override
	public String toString() {
		return this.protocal + "" + this.hostname;
	}
}