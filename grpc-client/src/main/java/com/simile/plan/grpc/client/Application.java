package com.simile.plan.grpc.client;

/**
 * @Author yitao
 * @Created 2021/09/29
 */
public class Application {

	public static void main(String[] args) {
		Client client = new Client("localhost:8980");
		System.out.println(client.hello("tom"));
	}

}
