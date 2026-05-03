package org.mockmc.mockmc.command.brigadier.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import org.bukkit.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.command.CommandSourceStackMock;
import org.mockmc.mockmc.generated.server.com.mojang.brigadier.arguments.ArgumentTypeBaseMock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class PlayerArgumentTypeMockTest
{

	@MockMCInject
	private ServerMock serverMock;

	@Test
	void playerArgumentType_ResolveByName() throws CommandSyntaxException
	{
		var reader = new StringReader("notch");

		var notch = serverMock.addPlayer("notch");
		var stack = CommandSourceStackMock.from(notch);

		var actual = parse(ArgumentTypes.player(), reader, notch).resolve(stack);

		assertEquals(List.of(notch), actual);
	}

	@Test
	void playerArgumentType_ResolveBySelector_All_FailsForSingle() throws CommandSyntaxException
	{
		var reader = new StringReader("@a");

		serverMock.addPlayer("notch");
		serverMock.addPlayer("dinnerbone");
		var stack = CommandSourceStackMock.from(serverMock.getConsoleSender());

		var actual = parse(ArgumentTypes.player(), reader, serverMock.getConsoleSender());

		assertEquals(List.of(), actual.resolve(stack));
	}

	@Test
	void playersArgumentType_ResolveByName() throws CommandSyntaxException
	{
		var reader = new StringReader("notch");

		var notch = serverMock.addPlayer("notch");
		var stack = CommandSourceStackMock.from(serverMock.getConsoleSender());

		var actual = parse(ArgumentTypes.players(), reader, serverMock.getConsoleSender()).resolve(stack);

		assertEquals(List.of(notch), actual);
	}

	@Test
	void playersArgumentType_ResolveBySelector_All() throws CommandSyntaxException
	{
		var reader = new StringReader("@a");

		var notch = serverMock.addPlayer("notch");
		var dinnerbone = serverMock.addPlayer("dinnerbone");
		var stack = CommandSourceStackMock.from(serverMock.getConsoleSender());

		var actual = parse(ArgumentTypes.players(), reader, serverMock.getConsoleSender()).resolve(stack);

		assertEquals(List.of(notch, dinnerbone), actual);
	}

	@Test
	void playersArgumentType_ResolveBySelector_NearestPlayer() throws CommandSyntaxException
	{
		var reader = new StringReader("@p");

		var notch = serverMock.addPlayer("notch");
		var dinnerbone = serverMock.addPlayer("dinnerbone");
		notch.teleport(new Location(null, 0, 0, 0));
		dinnerbone.teleport(new Location(null, 100, 100, 100));

		var stack = CommandSourceStackMock.from(notch);

		var actual = parse(ArgumentTypes.players(), reader, notch).resolve(stack);

		assertEquals(List.of(notch), actual);
	}

	@Test
	void playersArgumentType_ResolveBySelector_RandomPlayer() throws CommandSyntaxException
	{
		var reader = new StringReader("@r");

		var notch = serverMock.addPlayer("notch");
		var stack = CommandSourceStackMock.from(serverMock.getConsoleSender());

		var actual = parse(ArgumentTypes.players(), reader, serverMock.getConsoleSender()).resolve(stack);

		assertEquals(1, actual.size());
		assertEquals(notch, actual.getFirst());
	}

	@Test
	void playersArgumentType_ResolveBySelector_Self() throws CommandSyntaxException
	{
		var reader = new StringReader("@s");

		var notch = serverMock.addPlayer("notch");
		var stack = CommandSourceStackMock.from(notch);

		var actual = parse(ArgumentTypes.players(), reader, notch).resolve(stack);

		assertEquals(List.of(notch), actual);
	}

	@Test
	void playersArgumentType_ResolveBySelector_Self_ConsoleFails() throws CommandSyntaxException
	{
		var reader = new StringReader("@s");

		var console = serverMock.getConsoleSender();
		var stack = CommandSourceStackMock.from(console);

		var actual = parse(ArgumentTypes.players(), reader, console).resolve(stack);

		assertEquals(List.of(), actual);
	}

	@SuppressWarnings("unchecked")
	private <T> T parse(ArgumentType<T> type, StringReader reader, Object source) throws CommandSyntaxException
	{
		if (type instanceof ArgumentTypeBaseMock)
		{
			return ((ArgumentTypeBaseMock<T>) type).parse(reader, source);
		}
		// Fallback for when the classpath is actually correct (e.g. in some IDEs)
		try
		{
			var method = type.getClass().getMethod("parse", StringReader.class, Object.class);
			return (T) method.invoke(type, reader, source);
		} catch (Exception e)
		{
			return type.parse(reader);
		}
	}

}
