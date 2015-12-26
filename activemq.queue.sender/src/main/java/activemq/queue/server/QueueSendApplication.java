package activemq.queue.server;

import java.util.Date;

public class QueueSendApplication {

	public static void main(String[] args) {		
		
	ActiveMQQueueSender sender = new ActiveMQQueueSender("tcp://localhost:61616", "admin", "admin");
		
	try {
		sender.sendMessage("testQueue", "HI Marcin" + new Date());
	} catch (Exception e) {
		e.printStackTrace();
	}
	}


}
