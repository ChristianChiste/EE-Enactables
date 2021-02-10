package at.uibk.dps.ee.enactables.serverless.resource;

/**
 * Request to be sent to the scheduler.
 * 
 * @author Christian Chisté
 * 
 */
public class ResourceRequest {
	
	private String taskId;
	private RequestInformation requestInformation;
	
	
	public ResourceRequest(String taskId, RequestInformation requestInformation) {
		this.taskId=taskId;
		this.requestInformation=requestInformation;
	}
	
	public RequestInformation getRequestInformation() {
		return requestInformation;
	}
	
	public String getTaskId() {
		return taskId;
	}

}
