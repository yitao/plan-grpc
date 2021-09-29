package com.simile.plan.grpc.serve;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.ServerBuilder;

/**
 * @Author yitao
 * @Created 2021/09/29
 */
public class Server {
	private static final Logger logger = Logger.getLogger(Server.class.getName());

	private final int port;
	private final io.grpc.Server server;

	/**
	 * Create a RouteGuide server using serverBuilder as a base and features as data.
	 */
	public Server(int port) {
		this.port = port;
		ServerBuilder serverBuilder = ServerBuilder.forPort(port);
		serverBuilder.addService(new HelloServiceImpl());
		server = serverBuilder.build();
	}

	/**
	 * Start serving requests.
	 */
	public void start() throws IOException {
		server.start();
		logger.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				try {
					Server.this.stop();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}
				System.err.println("*** server shut down");
			}
		});
	}

	/**
	 * Stop serving requests and shutdown resources.
	 */
	public void stop() throws InterruptedException {
		if (server != null) {
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon threads.
	 */
	public void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

}
