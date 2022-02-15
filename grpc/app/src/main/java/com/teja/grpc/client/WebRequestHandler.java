package com.teja.grpc.client;

import com.teja.grpc.spec.HelloReply;
import com.teja.grpc.spec.HelloRequest;
import com.teja.grpc.spec.SimpleGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class WebRequestHandler
{
    @GrpcClient("grpc-server")
    private SimpleGrpc.SimpleBlockingStub simpleStub;

    public String sendMessage(final String aName)
    {
	try
	{
	    final HelloReply response = simpleStub.sayHello(HelloRequest.newBuilder().setName(aName).build());
	    return response.getMessage();
	}
	catch (final StatusRuntimeException aException)
	{
	    return "FAILED with " + aException.getStatus().getCode().name();
	}
    }
}