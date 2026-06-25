package org.mockmc.mockmc.command.brigadier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.mockmc.mockmc.command.CommandSourceStackMock;
import org.mockmc.mockmc.command.PluginVanillaCommandWrapperMock;
import org.mockmc.mockmc.command.VanillaCommandWrapperMock;
import org.mockmc.mockmc.command.WrappedLiteralCommandNode;

import java.lang.reflect.Field;
import java.util.Map;

public final class BrigadierUtils
{

	private static final String CHILDREN = "children";
	private static final String LITERALS = "literals";
	private static final String ARGUMENTS = "arguments";

	private static final CommandSourceStack DUMMY = new CommandSourceStackMock(new Location(null, 0, 0, 0),
			new DummyCommandSender(), null);

	private BrigadierUtils()
	{
		throw new IllegalStateException("Utility class");
	}

	public static WrappedLiteralCommandNode copyLiteral(final String newLiteral,
			final LiteralCommandNode<CommandSourceStack> source)
	{
		return new WrappedLiteralCommandNode(source, newLiteral);
	}

	public static void removeChildFromRoot(String name, CommandDispatcher<CommandSourceStack> dispatcher)
	{
		try
		{
			RootCommandNode<CommandSourceStack> rootNode = dispatcher.getRoot();
			getRootNodeMapFields(CHILDREN, rootNode).remove(name);
			getRootNodeMapFields(LITERALS, rootNode).remove(name);
			getRootNodeMapFields(ARGUMENTS, rootNode).remove(name);
		} catch (NoSuchFieldException | IllegalAccessException e)
		{
			throw new IllegalStateException("Failed to remove child node from root command dispatcher", e);
		}
	}

	public static void clearRoot(CommandDispatcher<CommandSourceStack> dispatcher)
	{
		try
		{
			RootCommandNode<CommandSourceStack> rootNode = dispatcher.getRoot();
			getRootNodeMapFields(CHILDREN, rootNode).clear();
			getRootNodeMapFields(LITERALS, rootNode).clear();
			getRootNodeMapFields(ARGUMENTS, rootNode).clear();
		} catch (NoSuchFieldException | IllegalAccessException e)
		{
			throw new IllegalStateException("Failed to clear root command dispatcher", e);
		}
	}

	@SuppressWarnings(
	{"unchecked", "java:S3011"})
	private static Map<String, ?> getRootNodeMapFields(String fieldName, RootCommandNode<CommandSourceStack> rootNode)
			throws IllegalAccessException, NoSuchFieldException
	{
		Field field = findCommandNodeField(fieldName);
		field.setAccessible(true);
		return (Map<String, ?>) field.get(rootNode);
	}

	private static Field findCommandNodeField(String fieldName) throws NoSuchFieldException
	{
		try
		{
			return CommandNode.class.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e)
		{
			Field field = scanFieldsByNameType(fieldName);
			if (field != null)
			{
				return field;
			}
			field = scanFieldsByOrder(fieldName);
			if (field != null)
			{
				return field;
			}
			throw e;
		}
	}

	private static Field scanFieldsByNameType(String fieldName)
	{
		Field result = null;
		for (Field declaredField : CommandNode.class.getDeclaredFields())
		{
			if (Map.class.isAssignableFrom(declaredField.getType()))
			{
				String lowerName = declaredField.getName().toLowerCase();
				if ((CHILDREN.equals(fieldName) && lowerName.contains("child"))
						|| (LITERALS.equals(fieldName) && lowerName.contains("literal"))
						|| (ARGUMENTS.equals(fieldName) && lowerName.contains("arg")))
				{
					result = declaredField;
					break;
				}
			}
		}
		return result;
	}

	private static Field scanFieldsByOrder(String fieldName)
	{
		java.util.List<Field> mapFields = new java.util.ArrayList<>();
		for (Field declaredField : CommandNode.class.getDeclaredFields())
		{
			if (Map.class.isAssignableFrom(declaredField.getType()))
			{
				mapFields.add(declaredField);
			}
		}
		int targetIndex = -1;
		if (CHILDREN.equals(fieldName))
		{
			targetIndex = 0;
		} else if (LITERALS.equals(fieldName))
		{
			targetIndex = 1;
		} else if (ARGUMENTS.equals(fieldName))
		{
			targetIndex = 2;
		}
		if (targetIndex >= 0 && targetIndex < mapFields.size())
		{
			return mapFields.get(targetIndex);
		}
		return null;
	}

	public static Command wrapNode(CommandNode<CommandSourceStack> node)
	{
		if (!(node instanceof WrappedLiteralCommandNode wrappedLiteralCommandNode))
		{
			throw new IllegalArgumentException("Unsure how to wrap a " + node);
		}
		ApiCommandMetaMock meta = wrappedLiteralCommandNode.getCommandMeta();
		if (meta == null)
		{
			return new VanillaCommandWrapperMock(node);
		}
		CommandNode<CommandSourceStack> argumentCommandNode = node.getRedirect() != null ? node.getRedirect() : node;
		String usage = getUsage(node, argumentCommandNode);

		if (meta.pluginMeta() == null)
		{
			return new VanillaCommandWrapperMock(node.getName(), meta.description(), usage, meta.aliases(), node,
					meta.helpCommandNamespace());
		}

		return new PluginVanillaCommandWrapperMock(node.getName(), meta.description(), usage, meta.aliases(), node,
				meta.plugin());
	}

	private static String getUsage(CommandNode<CommandSourceStack> node,
			CommandNode<CommandSourceStack> argumentCommandNode)
	{
		Map<CommandNode<CommandSourceStack>, String> map = PaperCommandsMock.INSTANCE.getDispatcherInternal()
				.getSmartUsage(argumentCommandNode, DUMMY);
		if (map.isEmpty())
		{
			return node.getUsageText();
		}
		return node.getUsageText() + " " + String.join("\n" + node.getUsageText() + " ", map.values());
	}

}
