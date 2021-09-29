package com.simile.plan.grpc.serve;

/**
 * @Author yitao
 * @Created 2021/09/29
 */
public class Application {


	/**
	 * Main method.  This comment makes the linter happy.
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server(8980);
		server.start();
		server.blockUntilShutdown();
	}


}
