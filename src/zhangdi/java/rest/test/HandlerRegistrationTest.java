package zhangdi.java.rest.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import zhangdi.java.rest.RESTfulContext;
import zhangdi.java.rest.RESTfulHandler;
import zhangdi.java.rest.RESTfulHandlerRegistration;
import zhangdi.java.rest.RESTfulPath;
import zhangdi.java.rest.impl.RESTfulHandlerRegistrationImpl;
import zhangdi.java.rest.impl.RESTfulHandlerRegistryImpl;

public class HandlerRegistrationTest {
	
	static RESTfulHandlerRegistryImpl registry;
	static RESTfulHandler testHandler;
	
	static class TestHandler implements RESTfulHandler {

		@Override
		public void handleGet(RESTfulContext context) throws IOException,
				ServletException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handlePost(RESTfulContext context) throws IOException,
				ServletException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handlePut(RESTfulContext context) throws IOException,
				ServletException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handleDelete(RESTfulContext context) throws IOException,
				ServletException {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@BeforeClass
	public static void setUpClass() {
		registry = new RESTfulHandlerRegistryImpl();
		testHandler = new TestHandler();
	}
	
	@AfterClass
	public static void tearDownClass() {
		registry = null;
		testHandler = null;
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
		registry.clear();
	}

	@Test
	public void testRegisterRESTfulHandlerRegistration() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = new RESTfulHandlerRegistrationImpl(path, testHandler);
		reg = registry.register(reg);
		assertNotNull("failure - RESTfulHandlerRegistration should not be null", reg);
		assertEquals("failure - Registered path should equal to the original path", reg.getPath(), path);
		assertEquals("failure - registered handler should equal to the original handler", reg.getHandler(), testHandler);
	}

	@Test
	public void testRegisterStringRESTfulHandler() {
		String path = "/junit/test";
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		assertNotNull("failure - RESTfulHandlerRegistration should not be null", reg);
		assertEquals("failure - Registered path should equal to the original path", reg.getPath().toString(), path);
		assertEquals("failure - registered handler should equal to the original handler", reg.getHandler(), testHandler);
	}

	@Test
	public void testRegisterRESTfulPathRESTfulHandler() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		assertNotNull("failure - RESTfulHandlerRegistration should not be null", reg);
		assertEquals("failure - Registered path should equal to the original path", reg.getPath(), path);
		assertEquals("failure - registered handler should equal to the original handler", reg.getHandler(), testHandler);
	}

	@Test
	public void testDeregisterRESTfulHandlerRegistration() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		registry.deregister(reg);
		reg = registry.find(path);
		
		assertNotEquals("failure - the handler should be deregistered", reg.getHandler(), testHandler);
		assertEquals("failure - the handler should be the default handler after deregistration", 
				reg.getHandler(), 
				registry.find(new RESTfulPath("/")).getHandler());
	}

	@Test
	public void testDeregisterString() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		registry.deregister("/junit/test");
		reg = registry.find(path);
		
		assertNotEquals("failure - the handler should be deregistered", reg.getHandler(), testHandler);
		assertEquals("failure - the handler should be the default handler after deregistration", 
				reg.getHandler(), 
				registry.find(new RESTfulPath("/")).getHandler());
	}

	@Test
	public void testDeregisterRESTfulPath() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		registry.deregister(path);
		reg = registry.find(path);
		
		assertNotEquals("failure - the handler should be deregistered", 
				reg.getHandler(), 
				testHandler);
		assertEquals("failure - the handler should be the default handler after deregistration", 
				reg.getHandler(), 
				registry.find(new RESTfulPath("/")).getHandler());
	}

	@Test
	public void testGetHandlerRegistrationRESTfulPath() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		assertEquals("failure - the registration instance should be same", 
				reg, 
				registry.getHandlerRegistration(path));
	}

	@Test
	public void testGetHandlerRegistrationString() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		assertEquals("failure - the registration instance should be same", 
				reg, 
				registry.getHandlerRegistration("/junit/test"));
	}

	@Test
	public void testFind() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		assertEquals("failure - the registration instance should be same", 
				reg, 
				registry.find(new RESTfulPath("/junit/test")));
	}
	
	@Test
	public void testAscendant() {
		RESTfulPath path = new RESTfulPath("/junit/test");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		RESTfulHandlerRegistration ascReg = registry.find(new RESTfulPath("/junit"));
		
		assertNotEquals("failure - the registration should not be same", reg, ascReg);
		assertEquals("failure - the ascendant handler should be the default one",
				ascReg.getHandler(),
				registry.find(new RESTfulPath("/")).getHandler());
	}
	
	@Test
	public void testDescendant() {
		RESTfulPath path = new RESTfulPath("/junit");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		RESTfulHandlerRegistration descReg = registry.find(new RESTfulPath("/junit/test"));
		
		assertNotEquals("failure - the registration should not be same", reg, descReg);
		assertEquals("failure - the descendant handler should be same one",
				descReg.getHandler(),
				reg.getHandler());
	}

}
