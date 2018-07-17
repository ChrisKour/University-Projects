import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RPCServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    private static int add(int a,int b){
        return (a+b);
    }

    public static void main(String[] args){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = null;
        try{
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare(RPC_QUEUE_NAME,false,false,false,null);
            channel.basicQos(1);
            System.out.println("Awaiting RPC requests");
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag,Envelope envelope,AMQP.BasicProperties properties,byte[] body) throws IOException{
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                            .Builder()
                            .correlationId(properties.getCorrelationId())
                            .build();

                    String response = "";
                    try{
                        String message = new String(body,"UTF-8");
                        String[] parts = message.split(" ");
                        int num1 = Integer.parseInt(parts[0]);
                        int num2 = Integer.parseInt(parts[1]);

                        System.out.println("Addition of: " + num1 + "," + num2);
                        response += add(num1,num2);
                    }
                    catch (RuntimeException e){
                        System.out.println(" [.] " + e.toString());
                    }
                    finally {
                        channel.basicPublish("",properties.getReplyTo(),replyProps,response.getBytes("UTF-8"));
                        channel.basicAck(envelope.getDeliveryTag(),false);
                        synchronized (this){
                            this.notify();
                        }
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME,false,consumer);
            while (true){
                synchronized (consumer){
                    try{
                        consumer.wait();
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null)
                try {
                    connection.close();
                }
                catch (IOException _ignore){}
        }
    }
}
