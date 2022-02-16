package com.teja.grpc.server;

import com.teja.sb.grpc.spec.pb.gen.HelloReply;
import com.teja.sb.grpc.spec.pb.gen.HelloRequest;
import com.teja.sb.grpc.spec.pb.gen.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public final class GRPCServer extends SimpleGrpc.SimpleImplBase
{
    private static final Logger log = LoggerFactory.getLogger(GRPCServer.class);

    @Override
    public void sayHello(final HelloRequest aHelloRequest, final StreamObserver<HelloReply> aHelloReplyStreamObserver)
    {
	log.info("say hello called");
	HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + aHelloRequest.getName()).build();
	aHelloReplyStreamObserver.onNext(reply);
	aHelloReplyStreamObserver.onCompleted();
    }
}
