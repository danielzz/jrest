package zhangdi.java.rest.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import zhangdi.java.rest.RESTfulHandler;
import zhangdi.java.rest.RESTfulHandlerRegistration;
import zhangdi.java.rest.RESTfulPath;
import zhangdi.java.rest.impl.RESTfulHandlerRegistryImpl;

public class ComplexRegistrationTest {
	
	static RESTfulHandler testHandler;
	static RESTfulHandlerRegistryImpl registry;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testHandler = new TestHandler();
		registry = new RESTfulHandlerRegistryImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		registry.clear();
	}

	@Test
	public void testNumberMatch() {
		RESTfulPath path = new RESTfulPath("/junit/test/[number]");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		RESTfulHandlerRegistration reg1 = registry.find("/junit/test/123");
		
		assertEquals("failure - /junit/test/123 should match the handler at /junit/test/[number]",
				reg.getHandler(),
				reg1.getHandler());
	}
	
	@Test
	public void testUUIDMatch() {
		RESTfulPath path = new RESTfulPath("/junit/test/[uuid]");
		RESTfulHandlerRegistration reg = registry.register(path, testHandler);
		
		RESTfulHandlerRegistration reg1 = registry.find("/junit/test/550e8400-e29b-41d4-a716-446655440000");
		
		assertEquals("failure - /junit/test/<uuid> should match the handler at /junit/test/[uuid]",
				reg.getHandler(),
				reg1.getHandler());
	}

}
