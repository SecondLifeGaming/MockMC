# RFC: Model Context Protocol (MCP) Server for AI Pair Programming

## 1. Executive Summary

AI coding assistants (Claude, Gemini, Cursor, Antigravity) are increasingly responsible for writing and maintaining Minecraft plugins and unit tests. However, standard LLMs suffer from severe knowledge cutoff issues regarding modern Paper releases (`26.x`), new item component models, Folia regional threading rules, and version-specific deprecations.

This RFC proposes the **MockMC Model Context Protocol (MCP) Server** a lightweight server implementing the standard Model Context Protocol (MCP) to provide AI agents with direct, real-time access to MockMC's API manifests, descriptor-level pairwise diff engine, and unit testing primitives.

---

## 2. Motivation & Use Cases

When an AI assistant is asked:

> _"Write a command that supports both Paper 1.20.6 and Paper 26.3, and tests it with MockMC."_

Today's models often hallucinate methods removed in 26.1, misuse Folia regional schedulers, or fail to handle modern Data Components properly.

With the **MockMC MCP Server**, any MCP-compatible agent can query the authoritative ground truth directly from the MockMC engine during code generation:

1. **Verify Binary Compatibility Before Suggesting Code**: Confirm whether a method or descriptor exists across the target version range.
2. **Retrieve Migration Pointers**: Ask what replaced a removed or deprecated Bukkit method.
3. **Scaffold High-Fidelity Tests**: Generate unit tests that properly configure MockMC server mocks, players, and region schedulers.

---

## 3. Server Architecture & Deployment Modes

```
+-------------------------------------------------------------+
|        AI Coding Agent (Antigravity, Cursor, Claude)        |
+-------------------------------------------------------------+
                              |
                     MCP Protocol (stdio)
                              v
+-------------------------------------------------------------+
|               Docker Container (Isolated)                   |
|  +-------------------------------------------------------+  |
|  |                 MockMC MCP Server                     |  |
|  |  - In-Memory Bytecode Manifests (1.20.6 - 26.3)       |  |
|  |  - VersionDiffEngine (Pairwise & Range Folding)       |  |
|  |  - MockMC Test Template Engine                        |  |
|  +-------------------------------------------------------+  |
+-------------------------------------------------------------+
```

### 3.1 Containerized Deployment (Recommended)

Running the MockMC MCP server in a lightweight container brings significant operational guarantees:
- **Consistent Execution Environment**: Guarantees identical Java bytecode parsing and diffing behavior across all developer workstations and CI runners without local JVM version friction.
- **Security & Sandboxing**: Enforces strict read-only filesystems and bounded memory limits, safeguarding against prompt-injection edge cases or context loops.
- **Frictionless Setup**: Configured via standard stdio-over-docker in client configs (`mcp_config.json`, `.gemini/settings.json`, or Cursor/Claude Desktop settings):

```json
{
  "mcpServers": {
    "mockmc": {
      "command": "docker",
      "args": [
        "run",
        "-i",
        "--rm",
        "ghcr.io/secondlifegaming/mockmc-mcp:latest"
      ]
    }
  }
}
```

### 3.2 Native Local Process Mode

For offline or container-restricted environments, the server may also be launched directly as a local process via Java:

```json
{
  "mcpServers": {
    "mockmc": {
      "command": "java",
      "args": [
        "-jar",
        "/path/to/mockmc-mcp-server.jar"
      ]
    }
  }
}
```

---

## 4. MCP Tools & Resources Specification

### 4.1 Tools

#### 1. `check_symbol_compatibility`

Verifies if a specific method, field, or class is available and binary-compatible across a target version range.

- **Parameters**:
    - `platform` (`string`): `"paper"` | `"folia"` | `"velocity"` | `"waterfall"`
    - `symbol` (`string`): e.g. `"org.bukkit.entity.Player#sendActionBar"`
    - `minVersion` (`string`): e.g. `"1.20.6"`
    - `maxVersion` (`string`): e.g. `"26.3"`
- **Response**:
    ```json
    {
        "compatible": true,
        "status": "STABLE",
        "timeline": [
            { "version": "1.20.6", "present": true, "descriptor": "(Lnet/kyori/adventure/text/Component;)V" },
            { "version": "26.3", "present": true, "descriptor": "(Lnet/kyori/adventure/text/Component;)V" }
        ],
        "notes": "Safe to use across entire target span."
    }
    ```

#### 2. `find_breaking_changes`

Enumerates all binary and source breaking changes between two versions for a specific platform or package namespace.

- **Parameters**:
    - `platform` (`string`): `"paper"` | `"folia"`
    - `fromVersion` (`string`): e.g. `"1.20.6"`
    - `toVersion` (`string`): e.g. `"26.3"`
    - `packageFilter` (`string`, optional): e.g. `"org.bukkit.inventory"`
- **Response**:
  List of removed classes, changed JVM descriptors, and added non-default interface methods with migration notes.

#### 3. `query_api_symbol`

Full-text search over the 16,000+ API symbols across modern Paper/Folia.

- **Parameters**:
    - `query` (`string`): e.g. `"customModelData"` or `"RegionScheduler"`
    - `platform` (`string`): `"paper"` | `"folia"`
- **Response**:
  Matching classes and methods with lifecycle disposition and parameter descriptors.

#### 4. `generate_mock_test_scaffold`

Returns recommended JUnit 5 + MockMC test boilerplate for testing a plugin event, command, or regional scheduler task.

- **Parameters**:
    - `testType` (`string`): `"command"` | `"listener"` | `"folia_region"` | `"inventory"`
    - `targetPlatform` (`string`): `"paper"` | `"folia"` | `"velocity"`

---

## 5. Security & Isolation

- **Read-Only Operation**: The MCP server operates in strictly deterministic read-only mode over embedded JSON manifests. It executes no external network requests or arbitrary bytecode evaluation.
- **Sub-Millisecond Performance**: Pre-indexed inverted search indexes allow instant answers with zero latency overhead in LLM workflows.

---

## 6. Delivery Milestones

1. **Protocol Core**: Author `MockMcMcpServer.java` using the Java MCP SDK.
2. **Tool Bindings**: Connect tools directly to `VersionDiffEngine` and `RangeDiffAggregator`.
3. **Containerization**: Build a lightweight multi-stage Dockerfile bundling the runtime manifests and server executable, with automated GitHub Actions pushing `ghcr.io/secondlifegaming/mockmc-mcp:latest` on release tags.
4. **Distribution & Client Integration**: Document configuration guidelines for standard IDE MCP clients (Claude Desktop, Cursor, Antigravity) and publish the standalone executable to Maven Central.
