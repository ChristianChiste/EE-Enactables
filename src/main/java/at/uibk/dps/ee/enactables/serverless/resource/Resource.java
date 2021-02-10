package at.uibk.dps.ee.enactables.serverless.resource;

/**
 * Contains a resource for a serverless function.
 * 
 * @author Christian Chisté
 * 
 */
public class Resource {

	private String resourceLink;
	
	
	public Resource(String resourceLink) {
		this.resourceLink=resourceLink;
	}

	public String getResourceLink() {
		return resourceLink;
	}

}
