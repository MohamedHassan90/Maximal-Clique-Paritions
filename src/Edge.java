class Edge {

	String vertex1;
	String vertex2;
	Boolean isEnabled;

	public Edge(String v1, String v2) {
		this.vertex1 = v1;
		this.vertex2 = v2;
		this.isEnabled = true;
	}

	public boolean contains(String s) {
		if (vertex1.equals(s) || vertex2.equals(s)) {
			return true;
		} else {
			return false;
		}
	}

}