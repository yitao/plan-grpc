package com.simile.plan.grpc.serve;

import com.simile.plan.grpc.serve.helloworld.HelloReply;
import com.simile.plan.grpc.serve.helloworld.HelloRequest;
import com.simile.plan.grpc.serve.helloworld.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @Author yitao
 * @Created 2021/09/29
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {


	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}


}
