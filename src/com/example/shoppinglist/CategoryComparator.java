package com.example.shoppinglist;

import java.util.Comparator;

public class CategoryComparator implements Comparator<ShoppingCategory> {

	@Override
	public int compare(ShoppingCategory arg0, ShoppingCategory arg1) {
		// TODO Auto-generated method stub
		return arg0.getCategoryName().compareTo(arg1.getCategoryName());
	}
}