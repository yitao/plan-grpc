package com.simile.plan.grpc.client;

import com.simile.plan.grpc.serve.helloworld.HelloReply;
import com.simile.plan.grpc.serve.helloworld.HelloRequest;
import com.simile.plan.grpc.serve.helloworld.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @Author yitao
 * @Created 2021/09/29
 */
public class Client {

	/**
	 * 通信频道
	 */
	protected ManagedChannel channel;
	/**
	 * grpc客户端
	 */
	protected HelloServiceGrpc.HelloServiceBlockingStub client;

	public Client(String url) {
		ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forTarget(url);
		//TODO 完善客户端连接配置
		this.channel = channelBuilder.usePlaintext().build();
		this.client = HelloServiceGrpc.newBlockingStub(channel);
	}

	public String hello(String name) {
		HelloReply reply = client.sayHello(HelloRequest.newBuilder().setName(name).build());
		return reply.getMessage();
	}

}
