package Project.message;

public class Response {
	private String status;
	private Object data;
	private Object data1;

	
	public Response(){
		
	}

	public Response(String status, Object data){
		this.status = status;
		this.data = data;
	}

	public Response(String status, Object data, Object data1) {
		this.status = status;
		this.data = data;
		this.data1 = data1;
	}

	public Object getData1() {
		return data1;
	}

	public void setData1(Object data1) {
		this.data1 = data1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
