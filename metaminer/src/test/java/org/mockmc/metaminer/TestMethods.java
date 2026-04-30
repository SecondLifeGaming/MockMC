package org.mockmc.metaminer;

import org.bukkit.entity.TNTPrimed;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestMethods
{
	@Test
	void runTest()
	{
		Map<String, Method> methodMap = new HashMap<>();
		for (Method m : TNTPrimed.class.getMethods())
		{
			if (java.lang.reflect.Modifier.isStatic(m.getModifiers()))
				continue;
			if (m.getDeclaringClass() == Object.class)
				continue;

			String sig = m.getName() + java.util.Arrays.toString(m.getParameterTypes());
			Method existing = methodMap.get(sig);
			if (existing != null)
			{
				if (existing.getReturnType().isAssignableFrom(m.getReturnType()))
				{
					methodMap.put(sig, m);
				}
			}
			else
			{
				methodMap.put(sig, m);
			}
		}

		assertTrue(methodMap.size() > 0, "The method map should contain at least one instance method from TNTPrimed");

		boolean foundSpigot = false;
		for (Method m : methodMap.values())
		{
			if (m.getName().equals("spigot"))
			{
				System.out.println("FOUND: " + m.getReturnType().getName() + " " + m.getName() + "()");
				foundSpigot = true;
			}
		}

		assertTrue(foundSpigot, "The method 'spigot()' should have been found in TNTPrimed");
	}
}
