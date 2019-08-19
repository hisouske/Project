package Object;

import java.util.ArrayList;

public class Customer_ {
	int tableNum;
	String customerName;
	private ArrayList<CustomOrder_> orderList = new ArrayList<>();

	public ArrayList<CustomOrder_> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<CustomOrder_> orderList) {
		this.orderList = orderList;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
