package cm.nci.pdf;



public class PdfDetails {
	
	private String date;
	private String description;
	private String amount;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PdfDetails [date=" + date + ", description=" + description
				+ ", amount=" + amount + "]";
	}
	
	

}
