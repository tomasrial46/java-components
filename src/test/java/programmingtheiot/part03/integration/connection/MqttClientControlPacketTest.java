/**
 * 
 * This class is part of the Programming the Internet of Things
 * project, and is available via the MIT License, which can be
 * found in the LICENSE file at the top level of this repository.
 * 
 * Copyright (c) 2020 by Andrew D. King
 */ 

package programmingtheiot.part03.integration.connection;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.*;
import programmingtheiot.gda.connection.*;

/**
 * This test case class contains very basic integration tests for
 * MqttClientControlPacketTest. It should not be considered complete,
 * but serve as a starting point for the student implementing
 * additional functionality within their Programming the IoT
 * environment.
 *
 */
public class MqttClientControlPacketTest
{
	// static
	
	private static final Logger _Logger =
		Logger.getLogger(MqttClientControlPacketTest.class.getName());
	
	
	// member var's
	
	private MqttClientConnector mqttClient = null;
	
	
	// test setup methods
	
	@Before
	public void setUp() throws Exception
	{
		this.mqttClient = new MqttClientConnector();
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	// test methods
	
	@Test
	public void testConnectAndDisconnect()
	{
		boolean isConnected = this.mqttClient.connectClient();
		assertTrue(isConnected);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean isDisconnected = this.mqttClient.disconnectClient();
		assertTrue(isDisconnected);
	}
	
	@Test
	public void testServerPing()
	{
		boolean isConnected = this.mqttClient.connectClient();
		assertTrue(isConnected);

		try {
			_Logger.info("Waiting to allow PINGREQ/PINGRESP packets to be generated...");
			Thread.sleep(7000);  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean isDisconnected = this.mqttClient.disconnectClient();
		assertTrue(isDisconnected);
	}
	
	@Test
	public void testPubSub()
	{
		boolean isConnected = this.mqttClient.connectClient();
		assertTrue(isConnected);

		ResourceNameEnum topic = ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE;

		boolean isSubscribed = this.mqttClient.subscribeToTopic(topic, 2);
		assertTrue(isSubscribed);

		// Publicar con QoS 1
		this.mqttClient.publishMessage(topic, "QoS 1 Message", 1);
		// Publicar con QoS 2
		this.mqttClient.publishMessage(topic, "QoS 2 Message", 2);

		try {
			Thread.sleep(3000); // Espera para recibir los mensajes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean isUnsubscribed = this.mqttClient.unsubscribeFromTopic(topic);
		assertTrue(isUnsubscribed);

		boolean isDisconnected = this.mqttClient.disconnectClient();
		assertTrue(isDisconnected);
	}
	
}
