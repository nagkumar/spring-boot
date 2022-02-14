package gae.piaz.grpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GRPCApplication
{
    public static void main(final String[] aArgs)
    {
	SpringApplication.run(GRPCApplication.class, aArgs);
    }
}
