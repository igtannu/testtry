package utils;

public enum APIResources {

	CreatePostAPI("/booking"),
	TokenAPI("/auth");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
}