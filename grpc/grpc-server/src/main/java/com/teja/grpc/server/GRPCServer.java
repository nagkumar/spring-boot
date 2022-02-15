package com.teja.grpc.server;

import com.teja.grpc.spec.HelloReply;
import com.teja.grpc.spec.HelloRequest;
import com.teja.grpc.spec.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class GRPCServer extends SimpleGrpc.SimpleImplBase
{
    private static final Logger log = LoggerFactory.getLogger(GRPCServer.class);

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver)
    {
	log.info("say hello called");
	HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
	responseObserver.onNext(reply);
	responseObserver.onCompleted();
    }
}
