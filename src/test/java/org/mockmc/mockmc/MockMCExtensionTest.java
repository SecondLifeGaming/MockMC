package org.mockmc.mockmc;

import org.bukkit.Server;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class MockMCExtensionTest
{

	@MockMCInject
	private ServerMock fieldServerMock;
	private final ServerMock constructorParameterServerMock;

	MockMCExtensionTest(@MockMCInject ServerMock constructorParameterServerMock)
	{
		this.constructorParameterServerMock = constructorParameterServerMock;
	}

	@Test
	void test_FieldServerMock_IsInjected()
	{
		assertNotNull(fieldServerMock);
	}

	@Test
	void test_ConstructorParameterServerMock_IsInjected()
	{
		assertNotNull(constructorParameterServerMock);
	}

	@Test
	void test_MethodParameterServerMock_IsInjected(@MockMCInject ServerMock methodParameterServerMock)
	{
		assertNotNull(methodParameterServerMock);
	}

	@Test
	void test_AlreadyMocking_TriesToMockAgain_ThrowsException()
	{
		assertThrowsExactly(IllegalStateException.class, MockMC::mock);
	}

	@Test
	void test_EnsureMocking_DoesNotThrowException()
	{
		assertDoesNotThrow(MockMC::ensureMocking);
	}

	@Test
	void test_IsMocked_ReturnsTrue()
	{
		assertTrue(MockMC.isMocked());
	}

	@Test
	void test_GetMock_ReturnsMock()
	{
		assertNotNull(MockMC.getMock());
	}

	@Test
	void test_GetOrCreateMock_ReturnsMock()
	{
		assertNotNull(MockMC.getOrCreateMock());
	}

	@Nested
	class WithNestedClassWithoutAnnotation
	{

		@MockMCInject
		private ServerMock nestedClassAnnotation;

		@Test
		void isFieldInjectedCorrectly()
		{
			assertNotNull(nestedClassAnnotation);
		}

		@Test
		void isParentFieldInjectedCorrectly()
		{
			assertNotNull(fieldServerMock);
		}

		@Test
		void isConstructorInjectedCorrectly()
		{
			assertNotNull(constructorParameterServerMock);
		}

	}

	@Nested
	@ExtendWith(MockMCExtension.class)
	class WithNestedClassWithAnnotation
	{

		@MockMCInject
		private ServerMock nestedClassAnnotation;

		@Test
		void isFieldInjectedCorrectly()
		{
			assertNotNull(nestedClassAnnotation);
		}

		@Test
		void isParentFieldInjectedCorrectly()
		{
			assertNotNull(fieldServerMock);
		}

		@Test
		void isConstructorInjectedCorrectly()
		{
			assertNotNull(constructorParameterServerMock);
		}

	}

	@Nested
	@ExtendWith(MockMCExtension.class)
	class WithBukkitServer
	{

		@MockMCInject
		private Server bukkitServer;

		@Test
		void fieldIsInjectedCorrectly()
		{
			assertNotNull(bukkitServer);
		}

	}

}
