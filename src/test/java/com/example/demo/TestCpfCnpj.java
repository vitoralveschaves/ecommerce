package com.example.demo;

import com.example.ecommerce.util.ValidarCNPJ;

public class TestCpfCnpj {
	
	public static void main(String[] args) {
		boolean isCnpj = ValidarCNPJ.isCNPJ("66.347.536/0001-96");
		System.out.println(isCnpj);
	}

}
