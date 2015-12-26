package activemq.queue.server;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQQueueSender {

	
private String serverURL;
private String brokerURL;
private String username;
private String password;

public ActiveMQQueueSender(final String brokerURL, String username, String password){
	this.brokerURL = brokerURL;
	this.username = username;
	this.password = password;
	
}

public void sendMessage(final String queueName, final String textMessage) throws Exception{
// get the connection factory
	Connection connection = null;
	Session session = null;
	

	try {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.username, this.password, this.brokerURL );
		//create connection
		connection = connectionFactory.createConnection();
		//start connection
		connection.start();
		//create Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//create queue
		Destination queue = session.createQueue(queueName);
		//create producer
		MessageProducer producer = session.createProducer(queue);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		TextMessage txtMessage = session.createTextMessage(textMessage);
		producer.send(txtMessage);
		System.out.println("Message sent to the queue" + queueName);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Exception while sending the message" + e);
		throw e;
	} finally {
		if (connection != null) {
			connection.close();
		}
		if (session != null) {
			session.close();
		}
		
	}
	
}

public String getServerURL() {
	return serverURL;
}

public void setServerURL(String serverURL) {
	this.serverURL = serverURL;
}	
	
}
